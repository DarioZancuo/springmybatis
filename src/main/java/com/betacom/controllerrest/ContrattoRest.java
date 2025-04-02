package com.betacom.controllerrest;

import com.betacom.entity.Contratto;
import com.betacom.services.interfaces.ContrattoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rest")
public class ContrattoRest {

    @Autowired
    private ContrattoServices contrattoS;

    @RequestMapping("/getContratto")
    @ResponseBody
    public Contratto getContratto(@RequestParam("id") int id) throws Exception {
        return contrattoS.getById(id);
    }
    
}
