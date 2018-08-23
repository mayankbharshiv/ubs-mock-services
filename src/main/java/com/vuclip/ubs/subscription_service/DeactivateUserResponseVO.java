package com.vuclip.ubs.subscription_service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeactivateUserResponseVO  {

	private boolean successful;
	private String message;
	private String responseCode;
	private SubscriptionStatusData userStatus;

}
