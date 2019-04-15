package com.wxd.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBo {

    @ApiModelProperty("订单号")
    private Long id;

    @ApiModelProperty("商家id")
    private Long shopId;

    @ApiModelProperty("商家名称")
    private String shopName;

    @ApiModelProperty("站点id")
    private Long merchId;

    @ApiModelProperty("站点名称")
    private String merchName;



}
