/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tools.stock.utils;

import java.net.URI;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

/**
 *
 * @author Faris
 */
public class HATEOSUtil {
    
    public static URI getURILinkedToControllerMethod(
            Class controller, 
            Object[] methodParams,
            String method,
            Class... methodParamTypes){
                
        try {
            return ControllerLinkBuilder
                    .linkTo(controller.getMethod(method, methodParamTypes), methodParams)
                    .toUri();
            
        } catch (NoSuchMethodException | SecurityException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
