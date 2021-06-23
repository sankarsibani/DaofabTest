package com.daofab.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Parent {

    private Long id;
    private String sender;
    private String receiver;
    private Long totalAmount;
    private Long totalPaidAmount;
}
