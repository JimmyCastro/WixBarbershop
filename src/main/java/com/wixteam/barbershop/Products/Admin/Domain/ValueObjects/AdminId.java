package com.wixteam.barbershop.Products.Admin.Domain.ValueObjects;

import com.wixteam.barbershop.Shared.Domain.Aggregate.CustomUUID;

public class AdminId extends CustomUUID {
    public AdminId(String value) {
        super(value);
    }
}
