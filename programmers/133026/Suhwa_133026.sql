-- 코드를 입력하세요
SELECT ICECREAM_INFO.INGREDIENT_TYPE, SUM(FIRST_HALF.TOTAL_ORDER)
FROM FIRST_HALF JOIN ICECREAM_INFO ON ICECREAM_INFO.FLAVOR= FIRST_HALF.FLAVOR
GROUP BY ICECREAM_INFO.INGREDIENT_TYPE