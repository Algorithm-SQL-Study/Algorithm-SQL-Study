# 쓰인 SQL 문법(MySQL 기준)

# ROUNT(숫자) -> 소수점 반올림해 정수로 만들어주는 내장 함수

# a LEFT JOIN b ON a.xx=b.xx : JOIN의 왼쪽의 테이블,즉 a를 기준으로 a의 모든 행에 ON 조건에 맞는 데이터가 존재하게끔함

# WHERE aa IN (dfdf,dfdf,dfd) : IN 뒤의 데이터리스트에 해당되는 데이터만 보이게함

#  subquery
-- SELECT col1, (SELECT ...) -- 스칼라 서브쿼리(Scalar Sub Query): 하나의 컬럼처럼 사용 (표현 용도)
-- FROM (SELECT ...)         -- 인라인 뷰(Inline View): 하나의 테이블처럼 사용 (테이블 대체 용도)
-- WHERE col = (SELECT ...)  -- 일반 서브쿼리: 하나의 변수(상수)처럼 사용 (서브쿼리의 결과에 따라 달라지는 조건절)

#WHERE 컬럼명 BETWEEN 시작범위 AND 종료범위 : mysql에서 날짜의 범위조건

#SELCT MAX(cola) ~~ GROUP BY col : cola의 데이터중 가장 큰것( MAX(cola)), goup by를 사용하면 기준 칼럼에 의해 그룹핑됨으로, 각 그룹별 데이터중 가장큰 cola 출력

# DATEDIFF(DATE1, DATE2) : DATE1에서 DATE2까지 일수 차이. DATE1이 미래일때 +, 더 과거이면 - 값


# 나의 코드
SELECT 
car.car_id	, car.car_type	, ROUND(car.daily_fee*30*(1-max(discount.discount_rate)/100)) AS FEE
FROM CAR_RENTAL_COMPANY_CAR as car LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN as discount
ON car.CAR_TYPE = discount.CAR_TYPE
WHERE car.CAR_TYPE IN ('세단','SUV') 
AND car.CAR_ID NOT IN 
    (
        SELECT CAR_ID 
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE (START_DATE BETWEEN '2020-11-01' AND '2020-11-30')
        OR (DATEDIFF('2022-11-01',START_DATE)>0 AND DATEDIFF(END_DATE,'2022-11-01') > 0)

    )
AND discount.DURATION_TYPE IN ('7일 이상', '30일 이상')
GROUP BY car.CAR_ID
HAVING (FEE >=500000 AND FEE < 2000000)
ORDER BY FEE DESC, car.CAR_TYPE ASC, car.CAR_ID DESC;




# GPT가 수정해준 코드
SELECT
    car.car_id,
    car.car_type,
    CAST(ROUND(car.daily_fee * 30 * (1 - IFNULL(discount.discount_rate, 0) / 100)) AS UNSIGNED) AS FEE
FROM CAR_RENTAL_COMPANY_CAR AS car
LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN AS discount
ON car.CAR_TYPE = discount.CAR_TYPE
   AND discount.DURATION_TYPE = '30일 이상'
WHERE car.CAR_TYPE IN ('세단', 'SUV')
   AND car.CAR_ID NOT IN (
        SELECT r.CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY r
        WHERE r.START_DATE <= '2022-11-30' AND r.END_DATE >= '2022-11-01'
   )
HAVING FEE >= 500000 AND FEE < 2000000
ORDER BY FEE DESC, car.car_type ASC, car.car_id DESC;

# IFNULL(discount.discount_rate, 0) : discount.discount_rate가 존재하면 할인율을 적용하고, 존재하지 않으면 0을 사용
# CAST(... AS UNSIGNED) : 결과를 부호 없는 정수로 캐스팅
# MYSQL에서 날짜 비교를 부등호를 통해 할수있음