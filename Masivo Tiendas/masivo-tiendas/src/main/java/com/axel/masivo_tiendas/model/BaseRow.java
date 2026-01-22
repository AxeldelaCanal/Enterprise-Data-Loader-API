package com.axel.masivo_tiendas.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseRow {

    private String storeId;      // 185458
    private String day;          // lunes, martes...
    private String startTime;    // 00:00:00
    private String endTime;      // 02:00:00
    private String tiendaPadre;  // Baez
    private String type;         // turbo, turbo_express, etc.
}
