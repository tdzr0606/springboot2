package com.nature.jet.pojo.au;

import lombok.Data;
import java.io.Serializable;

/**
 * 
 * PhotosList
 * Author:竺志伟
 * Date:2019-04-15 20:55:54
 */ 

@Data 
public class PhotosList  implements Serializable 
{
    private String imgUrl;
    private Integer photosId;
    private Integer id;
    private Integer sort;
    private String title;

}
