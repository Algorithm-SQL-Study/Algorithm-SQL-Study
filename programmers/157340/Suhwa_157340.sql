(
SELECT CAR_ID, '대여 가능' AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
WHERE CAR_ID NOT IN ( 
     SELECT CAR_ID
     FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
     WHERE DATEDIFF('2022-10-16',START_DATE)>=0 and DATEDIFF(END_DATE,'2022-10-16')>=0
 ))
 UNION
( SELECT CAR_ID, '대여중' AS AVAILABILITY
  FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
  WHERE DATEDIFF('2022-10-16',START_DATE)>=0 and DATEDIFF(END_DATE,'2022-10-16')>=0)
ORDER BY CAR_ID DESC;