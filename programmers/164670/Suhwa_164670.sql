
SELECT USED_GOODS_USER.USER_ID, USED_GOODS_USER.NICKNAME,CONCAT_WS(' ',USED_GOODS_USER.CITY,USED_GOODS_USER.STREET_ADDRESS1,USED_GOODS_USER.STREET_ADDRESS2) AS '전체주소',CONCAT(SUBSTRING(USED_GOODS_USER.TLNO,1,3),'-',SUBSTRING(USED_GOODS_USER.TLNO,4,4),'-',SUBSTRING(USED_GOODS_USER.TLNO,8,4)) AS '전화번호' 
FROM USED_GOODS_USER  
WHERE USED_GOODS_USER.USER_ID 
IN  (
SELECT USED_GOODS_BOARD.WRITER_ID AS w 
FROM USED_GOODS_BOARD 
GROUP BY USED_GOODS_BOARD.WRITER_ID 
HAVING COUNT(*)>2) 
ORDER BY USED_GOODS_USER.USER_ID DESC;
-- 01064534911