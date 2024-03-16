package com.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PulseEvent implements Vital{
        private String timeStamp;
}
