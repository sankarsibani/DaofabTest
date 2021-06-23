package com.daofab.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Child {

    private Long id;
    private Long parentId;
    private Long paidAmount;

}
