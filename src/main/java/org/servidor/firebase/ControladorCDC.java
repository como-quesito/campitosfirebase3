package org.servidor.firebase;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by campitos on 7/01/17.
 */

@Service
@Transactional
@Controller
public class ControladorCDC {

    @CrossOrigin
    @RequestMapping(value="/cdc", method= RequestMethod.GET,headers={"Accept=text/html"} )
    @ResponseBody
    public String enviarMensaje() throws Exception{
        String mensa="hola CDC";
// Ajustamos los headers
        HttpHeaders requestHeaders = new HttpHeaders();
       requestHeaders.setContentType(new MediaType("application","json"));

        //headers.add("Authorization:AIzaSyDhBeG0yER-PYaTnATRWRgFTLVZQqn8Nso");
       // requestHeaders.set("Authorization","key=AIzaSyDhBeG0yER-PYaTnATRWRgFTLVZQqn8Nso");
        requestHeaders.set("token","dhXQvlyIoJkXObarVYkWfjJxTxeMPnhQ");

        HttpEntity requestEntity = new HttpEntity<>(requestHeaders);

// Creaamos un RestTemplate
        RestTemplate restTemplate = new RestTemplate();

// Mandamos al uri del servicio web comomo consumimos normalmente un servicio Rest
        ResponseEntity<Estacion> responseEntity = restTemplate.exchange("https://www.ncdc.noaa.gov/cdo-web/api/v2/stations/GHCND:USC00121940", HttpMethod.GET, requestEntity, Estacion.class);
        Estacion estaciones = responseEntity.getBody();
        ObjectMapper maper=new ObjectMapper();

   System.out.println("TOOODO BIen "+estaciones.getElevation());

        Map map = new HashMap();
        map.put("success", true);
        ObjectMapper maper2=new ObjectMapper();
        return maper2.writeValueAsString(map);

    }

    @CrossOrigin
    @RequestMapping(value="/cdc2", method= RequestMethod.GET,headers={"Accept=text/html"} )
    @ResponseBody
    public String enviarMensaje2() throws Exception{
        String mensa="hola CDC2";
// Ajustamos los headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application","json"));

        //headers.add("Authorization:AIzaSyDhBeG0yER-PYaTnATRWRgFTLVZQqn8Nso");
        // requestHeaders.set("Authorization","key=AIzaSyDhBeG0yER-PYaTnATRWRgFTLVZQqn8Nso");
        requestHeaders.set("token","dhXQvlyIoJkXObarVYkWfjJxTxeMPnhQ");

        HttpEntity requestEntity = new HttpEntity<>(requestHeaders);

// Creaamos un RestTemplate
        RestTemplate restTemplate = new RestTemplate();

// Mandamos al uri del servicio web comomo consumimos normalmente un servicio Rest
        ResponseEntity<String> responseEntity = restTemplate.exchange("https://www.ncdc.noaa.gov/cdo-web/api/v2/data?datasetid=GHCND&locationid=ZIP:28801&startdate=2010-05-01&enddate=2010-05-05", HttpMethod.GET, requestEntity, String.class);
        String estaciones = responseEntity.getBody().toString();
        ObjectMapper maper=new ObjectMapper();




        return maper.writeValueAsString(estaciones);

    }
}
