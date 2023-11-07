select month(start_date) as MONTH, CAR_ID, count(*) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where car_id in (
    select car_id
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where start_date >= '2022-08-01' and start_date <= '2022-10-31'
    group by car_id
    having count(car_id)>4
    ) AND START_DATE BETWEEN "2022-08-01" AND "2022-10-31"
group by month, car_id
having records != 0
order by month, car_id desc;


