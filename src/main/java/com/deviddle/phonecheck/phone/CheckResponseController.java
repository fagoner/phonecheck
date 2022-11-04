package com.deviddle.phonecheck.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckResponseController {
    

    @Autowired
    private PhoneLibService phoneLibService;

    @PostMapping("")
    public CheckResponse check(
        @RequestBody CheckRequest checkRequest
    ) {
        return phoneLibService.check(checkRequest);
    }


}
