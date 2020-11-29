package com.dawn.controller;

import com.dawn.common.DawnCodingError;
import com.dawn.common.DawnCodingResult;
import com.dawn.dto.PaymentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.dawn.common.DawnErrorType.INVALID_REQUEST_BODY;

@Controller
public class PaymentController {


    @PostMapping("/payment")
    public ResponseEntity<DawnCodingResult> doPay(@RequestBody PaymentDTO.CreatePayment payment) {
        if(payment.getCardId() == 1234567890 && payment.getCardPassword().equals("test1234")){
            return new ResponseEntity<>(DawnCodingResult.OK(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(
                    new DawnCodingResult<>(
                            new DawnCodingError(
                                    INVALID_REQUEST_BODY,
                                    "결제 오류"), null),
                    HttpStatus.BAD_REQUEST);
        }
    }



}
