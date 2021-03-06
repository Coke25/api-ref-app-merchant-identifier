/*
 * Copyright 2016 MasterCard International.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials
 * provided with the distribution.
 * Neither the name of the MasterCard International Incorporated nor the names of its
 * contributors may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */

package com.mastercard.refapp.merchantid.controller;

import com.mastercard.api.core.exception.*;
import com.mastercard.api.core.model.RequestMap;
import com.mastercard.api.merchantidentifier.MerchantIdentifier;
import com.mastercard.refapp.merchantid.model.MerchantIdQueryRequest;
import com.mastercard.refapp.merchantid.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/merchantid", produces = Constants.APPLICATION_JSON_UTF8_VALUE)
public class MerchantIdentifierController{

    private static final Logger logger = LoggerFactory.getLogger(MerchantIdentifierController.class);

    @RequestMapping(value = "query", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MerchantIdentifier query(@RequestBody MerchantIdQueryRequest merchantIdQueryRequest) throws ApiException{

        logger.info("Requested merchant id: " + merchantIdQueryRequest.getMerchantId());

        RequestMap map = new RequestMap();
        map.put("MerchantId", merchantIdQueryRequest.getMerchantId());
        map.put("Type", merchantIdQueryRequest.getQueryType());

        MerchantIdentifier merchantIdentifier = MerchantIdentifier.query(map);

        return merchantIdentifier;
    }
}


