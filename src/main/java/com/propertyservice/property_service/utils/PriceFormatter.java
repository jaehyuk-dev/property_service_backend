package com.propertyservice.property_service.utils;

import com.propertyservice.property_service.domain.common.eums.TransactionType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PriceFormatter {

    public String format(Long price1, Long price2, TransactionType transactionType) {
        String formattedPrice1 = formatPrice(price1);
        String formattedPrice2 = (price2 != null) ? formatPrice(price2) : null;

        if (transactionType == TransactionType.JEONSE) {
            return formattedPrice1;
        } else {
            return formattedPrice1 + " / " + (formattedPrice2 != null ? formattedPrice2 : "0");
        }
    }

    private String formatPrice(Long price) {
        if (price == null) return "0";

        long billion = price / 100000000; // 억 단위
        long million = (price % 100000000) / 10000; // 만 단위
        long remainder = price % 10000; // 만 이하 단위

        StringBuilder result = new StringBuilder();

        if (billion > 0) {
            result.append(billion).append("억");
        }
        if (million > 0) {
            if (result.length() > 0) result.append(" ");
            result.append(million).append("만");
        }
        if (remainder > 0) {
            if (result.length() > 0) result.append(" ");
            result.append(remainder);
        }

        return result.toString();
    }
}
