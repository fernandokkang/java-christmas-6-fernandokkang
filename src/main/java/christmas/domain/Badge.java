package christmas.domain;

import christmas.constant.Price;

public enum Badge {

    EMPTY("없음"),
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private String type;

    private Badge(String type) {

        this.type = type;
    }

    public static Badge giveBadge(int benefitPrice) {

        if (benefitPrice >= Price.MINIMUM_BENEFIT_PRICE_RECEIVE_SANTA_BADGE) {
            return SANTA;
        }

        if (benefitPrice >= Price.MINIMUM_BENEFIT_PRICE_RECEIVE_TREE_BADGE) {
            return TREE;
        }

        if (benefitPrice >= Price.MINIMUM_BENEFIT_PRICE_RECEIVE_STAR_BADGE) {
            return STAR;
        }

        return EMPTY;
    }
    public String getType() {
        return type;
    }
}
