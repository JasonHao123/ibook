package org.ops4j.pax.web.samples.spring.web.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

// @Service
public class EchoService implements IEcho {

    @Override
    @PreAuthorize("hasRole('supervisor')")
    public String echo(String text) {
        // TODO Auto-generated method stub
        return null;
    }

}
