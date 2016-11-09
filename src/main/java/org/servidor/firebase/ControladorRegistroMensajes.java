package org.servidor.firebase;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gcm.server.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by campitos on 5/26/15.
 */

@Service
@Transactional
@Controller
public class ControladorRegistroMensajes {
    //String key="AIzaSyDeDwyXm8mG_EwBtqsaS98z1FGqoqc2BRM";
    //String key ="AIzaSyCskiTTTj8NKj3Kg4kKpfORK4u86uhqZrk";


    //Esta es del proyecto
   // https://console.firebase.google.com/project/primer-firebase/overview
    //Recuerda que en la gogole console automaticamente ya aparece este proyecto pero en el archivo que se generald e
    //firebase (el json) solo tienes los accesos de la app android, para el servidor en tu consola debes activar
    //un api de servidor, la cual es la siguiente para dicho proyecto
    String key= "AIzaSyDhBeG0yER-PYaTnATRWRgFTLVZQqn8Nso";
    private static final int MULTICAST_SIZE = 1000;
    private Sender sender;
    private static final Executor threadPool = Executors.newFixedThreadPool(5);


    @Autowired RepositorioClave repoClave;


    @RequestMapping(value="/registro-mensajes", method= RequestMethod.POST, headers={"Accept=application/json;charset=UTF-8"})
    @ResponseBody
    String guardarCliente(@RequestBody String json)throws Exception{
        ObjectMapper mapper=new ObjectMapper();

      String resultado="nada";
        //convertimos jsonsito a un RegistroMensajeria, que asi se supone vienen :)
        Clave registro = mapper.readValue(json,Clave.class );
              repoClave.save(registro);
        System.out.println("Registrado con exito");
        return "registrado con exito";
    }

    @RequestMapping(value="/enviar/{mensaje}", method= RequestMethod.GET,headers={"Accept=text/html"} )
    @ResponseBody
    public String enviarMensaje(@PathVariable String mensaje){

  Clave clave=repoClave.findOne(6L);

        Mensaje mensa = new Mensaje();
        mensa.setTo(clave.getToken());
        Map<String,String> mapa=new HashMap<String, String>();
        Data data =new Data();
        data.setTitle("yo");
        data.setBody(mensaje);
        mensa.setData(data);
// Set the Content-Type header
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application","json"));

        //headers.add("Authorization:AIzaSyDhBeG0yER-PYaTnATRWRgFTLVZQqn8Nso");
        requestHeaders.set("Authorization","key=AIzaSyDhBeG0yER-PYaTnATRWRgFTLVZQqn8Nso");

        HttpEntity<Mensaje> requestEntity = new HttpEntity<Mensaje>(mensa, requestHeaders);

// Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

// Make the HTTP POST request, marshaling the request to JSON, and the response to a String
        ResponseEntity<String> responseEntity = restTemplate.exchange("https://fcm.googleapis.com/fcm/send", HttpMethod.POST, requestEntity, String.class);
        String resultado = responseEntity.getBody();

        return "mensaje enviado con exito"+resultado;

    }

    @RequestMapping(value="/datos", method= RequestMethod.GET,headers={"Accept=application/json"} )
    @ResponseBody
    public String datos()throws Exception{
     Mensaje mensa=new Mensaje();
        mensa.setTo("asasasasas");
        Data noti=new Data();
        noti.setBody("cuerpo");
        noti.setTitle("titututuutut");
        mensa.setData(noti);
  ObjectMapper maper=new ObjectMapper();
      return   maper.writeValueAsString(mensa);


    }
}
