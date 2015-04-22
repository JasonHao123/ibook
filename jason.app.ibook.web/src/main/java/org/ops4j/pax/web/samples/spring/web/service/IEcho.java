package org.ops4j.pax.web.samples.spring.web.service;

import org.springframework.security.access.prepost.PreAuthorize;



public interface IEcho {
    @PreAuthorize("hasRole('supervisor')")
    public String echo(String text);
}
