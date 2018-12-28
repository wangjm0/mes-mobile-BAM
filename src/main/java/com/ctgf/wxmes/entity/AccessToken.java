package com.ctgf.wxmes.entity;

import lombok.Data;

@Data
public class AccessToken
{
	private String token;
	private Integer expiresIn;
}
