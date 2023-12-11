-- 코드를 입력하세요

SELECT CONCAT('/home/grep/src/',USED_GOODS_FILE.BOARD_ID, '/',USED_GOODS_FILE.FILE_ID,USED_GOODS_FILE.FILE_NAME,USED_GOODS_FILE.FILE_EXT)
FROM USED_GOODS_FILE
WHERE USED_GOODS_FILE.BOARD_ID = (SELECT USED_GOODS_BOARD.BOARD_ID
FROM USED_GOODS_BOARD ORDER BY USED_GOODS_BOARD.VIEWS DESC LIMIT 1)
ORDER BY USED_GOODS_FILE.FILE_ID DESC;