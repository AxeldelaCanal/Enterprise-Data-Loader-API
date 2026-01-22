package com.axel.masivo_tiendas.service;

import lombok.Builder;  
import lombok.Data;

@Data
@Builder
public class BaseRow{
    private String storeId;
    private String day;
    private String startTime;
    private String endTime;
    private String tiendaPadre;
    private String type;
}