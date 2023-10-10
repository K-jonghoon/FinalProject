SELECT u.USERS_NAME AS users_name, q.QST_TITLE, q.QST_CONTENT,
	 q.QST_REGDATE, r.RPY_SEQ, r.RPY_ID, r.RPY_REF, 
	 r.RPY_CONTENT , r.RPY_STATUS, r.RPY_REGDATE
	FROM QUESTBOARD q, USERSINFO u, REPLYBOARD r, HOSPITAL h
	WHERE q.QST_ID = u.USERS_ID 
	AND q.QST_SEQ = r.RPY_REF
	AND r.RPY_ID = h.HOSP_ID
	AND (q.QST_STATUS = 'Y' OR r.RPY_STATUS = 'Y')
	AND q.QST_SEQ = '#{qst_seq}'
    ORDER BY r.RPY_SEQ DESC;
    
   SELECT q.*, r.*
	FROM USERSINFO u 
	inner join QUESTBOARD q on q.QST_ID = u.USERS_ID 
	left join REPLYBOARD r on q.QST_SEQ = r.RPY_REF	
	left join HOSPITAL h on h.HOSP_ID = r.RPY_ID
	where
	(q.QST_STATUS != 'N' OR r.RPY_STATUS != 'N')
	 and q.QST_SEQ = 'Q9'
    ORDER BY r.RPY_SEQ DESC;
    
   
   SELECT q.QST_SEQ, q.QST_ID, u.USERS_NAME, q.QST_TITLE, q.QST_CONTENT, q.QST_SPECIES, q.QST_PART, q.QST_REGDATE, q.QST_FAST
	FROM QUESTBOARD q JOIN USERSINFO u  
	ON q.QST_ID = u.USERS_ID 
	WHERE q.QST_STATUS != 'N'
    ORDER BY q.QST_FAST DESC, q.QST_REGDATE DESC ;
   
   
   SELECT q.QST_ID, u.USERS_NAME
	FROM USERSINFO u JOIN QUESTBOARD q 
	ON q.QST_ID = u.USERS_ID;
	

SELECT q.QST_SEQ, q.QST_ID, u.USERS_NAME, q.QST_TITLE, q.QST_CONTENT, q.QST_SPECIES, ANM_SPECIES, q.QST_PART, q.QST_REGDATE, q.QST_FAST
	FROM QUESTBOARD q JOIN USERSINFO u  
	ON q.QST_ID = u.USERS_ID 
	JOIN ANIMALCODE a
	ON q.QST_SPECIES = a.ANM_CODE
	WHERE q.QST_STATUS != 'N'
    ORDER BY q.QST_FAST DESC, q.QST_REGDATE DESC ;
    
   
-- 미리보기에서 글자 수 초과 시 ... 적용을 위한 더미데이터 
--INSERT INTO QUESTBOARD(QST_SEQ, QST_ID, QST_TITLE, QST_CONTENT, QST_SPECIES, QST_PART, QST_REGDATE, QST_FAST, QST_STATUS)
--VALUES((SELECT CONCAT('Q', NVL(MAX(TO_NUMBER(SUBSTR(QST_SEQ,2))), 0)+1) AS QST_SEQ FROM QUESTBOARD),'merida@disney.com', '다소 긴 질문입니다.', 
--'질문이 있습니다. 중요한 질문입니다. 하지만 급한 질문은 아닙니다. 질문의 중요성이 반드시 긴급성과 비례해야 한다고 생각하지는 않습니다. 저에게는 강아지가 한 마리 있습니다. 고양이일 수도 있습니다. 사실은 코드를 확인하지 않아서 기억이 나지 않습니다. 하지만 그것 또한 중요한 게 아닙니다. 이것은 질문이 아니기 때문입니다. 질문에는 답변이 있어야 하지만 질문이 아니라면 답변이 필요 없습니다. 개인가 고양이인가는 관측하는 순간 결정될 것입니다. 하지만 동물들은 공통적으로 외피를 가지므로, 이 글을 외피에 대한 질문이라고 이해하시면 무리가 없을 듯 합니다. 동물의 침은 서로 다른 종의 상처에도 효과가 있습니까? 개가 생선을, 개구리가 참새를 핥아도 서로에게 소독과 진정효과를 줄 수 있습니까? 아니면 침에서 비롯되는 치유효과는 포유류의 침에만 해당되는 것입니까? 다친 동물은 하나이지만 제게 의탁한 동물은 여럿입니다. 도움이 필요합니다.',
--'Z', '01', CURRENT_DATE , 'N' , 'Y' );
INSERT INTO QUESTBOARD(QST_SEQ, QST_ID, QST_TITLE, QST_CONTENT, QST_SPECIES, QST_PART, QST_REGDATE, QST_FAST, QST_STATUS)
VALUES('Q27','merida@disney.com', '다소 긴 질문입니다.', 
'질문이 있습니다. 중요한 질문입니다. 하지만 급한 질문은 아닙니다. 질문의 중요성이 반드시 긴급성과 비례해야 한다고 생각하지는 않습니다. 저에게는 강아지가 한 마리 있습니다. 고양이일 수도 있습니다. 사실은 코드를 확인하지 않아서 기억이 나지 않습니다. 하지만 그것 또한 중요한 게 아닙니다. 이것은 질문이 아니기 때문입니다. 질문에는 답변이 있어야 하지만 질문이 아니라면 답변이 필요 없습니다. 개인가 고양이인가는 관측하는 순간 결정될 것입니다. 하지만 동물들은 공통적으로 외피를 가지므로, 이 글을 외피에 대한 질문이라고 이해하시면 무리가 없을 듯 합니다. 동물의 침은 서로 다른 종의 상처에도 효과가 있습니까? 개가 생선을, 개구리가 참새를 핥아도 서로에게 소독과 진정효과를 줄 수 있습니까? 아니면 침에서 비롯되는 치유효과는 포유류의 침에만 해당되는 것입니까? 다친 동물은 하나이지만 제게 의탁한 동물은 여럿입니다. 도움이 필요합니다.',
'Z', '01', CURRENT_DATE , 'N' , 'Y' );
		
INSERT INTO PROJECT.REPLYBOARD
(RPY_SEQ, RPY_ID, RPY_REF, RPY_CONTENT, RPY_REGDATE, RPY_STATUS, RPY_CHOSEN)
VALUES('R11', 'gana@naver.com', 'Q22', '캐터피 잘 보고 갑니다. 저희병원에 오셔서 물고기 피부를 활용한 강아지 피부 치료 코스를 받아보시기 바랍니다.', SYSDATE , 'Y' , 'N' );

		
INSERT INTO PROJECT.QUESTBOARD
(QST_SEQ, QST_ID, QST_TITLE, QST_CONTENT, QST_SPECIES, QST_PART, QST_REGDATE, QST_FAST, QST_STATUS)
VALUES('', '', '', '', '', '', SYSDATE , 'N' , 'Y' );

INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q25', 'elsa@disney.com', '슬개골 탈구 질문', 
'저희집 거실이 얼음 바닥이라 그런지, 후추가 자주 미끄러지곤 하는데 병원에 가서 검사를 받아봐야 할까요? 그리고 바닥에 카펫을 깔아주는게 좋은가요? 후추가 성 안에서도 마음껏 뛰어놀 수 있는 환경을 만들어주고 싶어요.', 
'A', '06', TO_DATE('2023-09-28 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'Y', 'Y');

INSERT INTO PROJECT.REPLYBOARD(RPY_SEQ, RPY_ID, RPY_REF, RPY_CONTENT, RPY_REGDATE, RPY_STATUS, RPY_CHOSEN)
VALUES('R11', 'gana@naver.com', 'Q25', '바닥이 미끄럽고 축축한 곳은 강아지가 뛰어놀기에 적합하지 않습니다. 마찰력이 있는 카펫이나 매트 등을 깔아주시는 것도 좋지만 야외로 매일 산책을 시켜주시는게 중요합니다. 강아지가 가끔 미끄러지는 것은 큰 문제가 없겠지만 걸음걸이가 불편해 보인다면 즉시 병원에 방문하여 검사를 받으시기 바랍니다.', 
TO_DATE('2023-09-28 10:19:00', 'YYYY-MM-DD HH24:MI:SS'), 'Y' , 'N' );

INSERT INTO PROJECT.REPLYBOARD(RPY_SEQ, RPY_ID, RPY_REF, RPY_CONTENT, RPY_REGDATE, RPY_STATUS, RPY_CHOSEN)
VALUES('R12', 'dara@naver.com', 'Q25', '강아지가 자주 미끄러진다면 관절에 충분히 무리가 갈 수 있습니다. 증상이 없더라도 내원하여 검사를 받으시고, 가급적 빨리 실내환경을 개선해주시는게 좋겠습니다.', 
TO_DATE('2023-09-28 11:19:00', 'YYYY-MM-DD HH24:MI:SS'), 'Y' , 'N' );

INSERT INTO PROJECT.REPLYBOARD(RPY_SEQ, RPY_ID, RPY_REF, RPY_CONTENT, RPY_REGDATE, RPY_STATUS, RPY_CHOSEN)
VALUES('R13', 'maba@naver.com', 'Q25', '견종에 따라 슬개골이나 관절의 내구성이 다를 수 있습니다. 이미 강아지가 몇 번 미끄러진 경험이 있다면 병원에 방문하여 검사를 진행하시고, 실내보다는 실외에서 뛰어놀 수 있도록 환경을 조성해주세요.', 
TO_DATE('2023-09-28 11:19:00', 'YYYY-MM-DD HH24:MI:SS'), 'Y' , 'N' );
		
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q26', 'anna@disney.com', '집고양이 생고기 급여', 
'집에 있는 까미한테 밖에서 잡아 온 순록 고기를 줘봤어요. 평생 사료만 먹고 사는건 고양이한테도 재미없을 것 같아서요. 까미도 거부감 없이 잘 먹었구요. 그런데 오늘 아침에 보니 어제 먹은걸 다 토해놓았네요. 집고양이한테 야생동물 고기를 주면 안되는 건가요? ', 
'B', '04', TO_DATE('2023-09-29 13:13:00', 'YYYY-MM-DD HH24:MI:SS'), 'Y', 'Y');

INSERT INTO PROJECT.REPLYBOARD(RPY_SEQ, RPY_ID, RPY_REF, RPY_CONTENT, RPY_REGDATE, RPY_STATUS, RPY_CHOSEN)
VALUES('R14', 'gana@naver.com', 'Q26', '고양이의 구토는 헤어볼 때문일 가능성이 높으나, 식습관이 사료에 맞춰진 집고양이에게 야생동물의 고기는 위험할 수 있습니다. 알려지지 않은 오염이나 기생충이 있을 수 있으니 내원하셔서 검사받으시기 바랍니다.', TO_DATE('2023-09-29 14:23:00', 'YYYY-MM-DD HH24:MI:SS'), 'Y' , 'N' );

INSERT INTO PROJECT.REPLYBOARD(RPY_SEQ, RPY_ID, RPY_REF, RPY_CONTENT, RPY_REGDATE, RPY_STATUS, RPY_CHOSEN)
VALUES('R15', 'dara@naver.com', 'Q26', '고기를 먹은 다음 날 바로 토했다면 충분히 야생동물의 고기가 원인일 수 있습니다. 일단 병원에 방문하셔서 기생충과 알러지 검사를 받아보시는게 좋겠습니다.', TO_DATE('2023-09-29 16:23:00', 'YYYY-MM-DD HH24:MI:SS'), 'Y' , 'N' );
	

INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q28', 'jasmine@disney.com', '강아지 피부가 보들보들해요', 
'너무 비단처럼 보들보들해요. 이거 뱀인가요?', 
'A', '01', TO_DATE('2023-08-24 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q29', 'jasmine@disney.com', '강아지 피부가 까슬까슬해요', 
'너무 사포처럼 까슬까슬해요. 이거 두드러기인가요?', 
'A', '01', TO_DATE('2023-08-23 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q30', 'jasmine@disney.com', '강아지 피부가 물렁물렁해요', 
'너무 물풍선처럼 물렁물렁해요. 이거 인절미인가요?', 
'A', '01', TO_DATE('2023-08-22 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q32', 'jasmine@disney.com', '강아지 털이 살랑살랑해요', 
'바람이 불면 강아지 털이 살랑살랑해요. 이거 갈대인가요?', 
'A', '01', TO_DATE('2023-08-21 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q33', 'jasmine@disney.com', '강아지 발바닥이 말랑말랑해요', 
'강아지 발바닥이 너무 젤리처럼 말랑말랑해요. 이거 하리보인가요?', 
'A', '01', TO_DATE('2023-08-20 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q34', 'jasmine@disney.com', '강아지가 가끔 절뚝거려요', 
'미끄러진 적 없는데도 슬개골이 망가질 수가 있나요?', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q35', 'jasmine@disney.com', '강아지35', 
'강아지35', 
'A', '01', TO_DATE('2023-08-18 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q36', 'jasmine@disney.com', '강아지36', 
'강아지36', 
'A', '01', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q37', 'jasmine@disney.com', '강아지37', 
'강아지37', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q38', 'jasmine@disney.com', '강아지38', 
'강아지38', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q39', 'jasmine@disney.com', '강아지39', 
'강아지39', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q40', 'jasmine@disney.com', '강아지40', 
'강아지40', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q41', 'jasmine@disney.com', '강아지41', 
'강아지41', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q42', 'jasmine@disney.com', '강아지42', 
'강아지42', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q43', 'jasmine@disney.com', '강아지가43', 
'강아지43', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q44', 'jasmine@disney.com', '강아지44', 
'강아지44', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q45', 'jasmine@disney.com', '강아지45', 
'강아지45', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q46', 'jasmine@disney.com', '강아지46', 
'강아지46', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q47', 'jasmine@disney.com', '강아지47', 
'강아지47', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q48', 'jasmine@disney.com', '강아48', 
'미강아지48', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q49', 'jasmine@disney.com', '강아지49', 
'강아지49', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q50', 'jasmine@disney.com', '강아지50', 
'강아지50', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q51', 'jasmine@disney.com', '강아지1', 
'강아지51', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q52', 'jasmine@disney.com', '강아요52', 
'강아지52?', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q53', 'jasmine@disney.com', '강아지53', 
'강아지53', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q54', 'jasmine@disney.com', '강아지54', 
'강아지54', 
'A', '06', TO_DATE('2023-08-19 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q55', 'jasmine@disney.com', '강아지가 산책을 안나가려고 해요.', 
'아파서 그러는 걸까요? 슬개골 때문에?', 
'A', '06', TO_DATE('2023-08-18 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q56', 'jasmine@disney.com', '강아지가 몸에 진흙을 문질러요.', 
'햇볕이 뜨거워서 그러는 걸까요? 화상을 입어서???', 
'A', '01', TO_DATE('2023-08-17 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');
INSERT INTO QUESTBOARD 	(QST_SEQ, QST_ID, QST_TITLE,QST_CONTENT, QST_SPECIES, QST_PART,QST_REGDATE, QST_FAST, QST_STATUS)
VALUES ('Q57', 'jasmine@disney.com', '강아지가 몸에 찰흙을 문질러요.', 
'앞발로 자꾸 강아지가 찰흙을 조각해요. 강아지도 앞발을 많이 쓰면 똑똑해지는 걸까요?', 
'A', '01', TO_DATE('2023-08-16 09:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'N', 'Y');





		SELECT QST_SEQ, QST_ID, QST_TITLE, QST_CONTENT, QST_STATUS, QST_REGDATE, QST_FAST,
  			USERS_NAME, QST_SPECIES, ANM_SPECIES, QST_PART, PART_NAME,
				ROW_NUMBER() OVER(ORDER BY QST_FAST DESC, QST_REGDATE DESC) rn
		 	FROM QUESTBOARD q JOIN USERSINFO u  
				ON q.QST_ID = u.USERS_ID 
				JOIN ANIMALCODE a1
				ON q.QST_SPECIES = a1.ANM_CODE
				JOIN ANIMALPART a2
				ON q.QST_PART = a2.PART_CODE ;
			
  	SELECT 	QST_SEQ, QST_ID, QST_TITLE, QST_CONTENT, QST_STATUS, QST_REGDATE, QST_FAST,
  			USERS_NAME, QST_SPECIES, ANM_SPECIES, QST_PART, PART_NAME
	FROM (
		SELECT QST_SEQ, QST_ID, QST_TITLE, QST_CONTENT, QST_STATUS, QST_REGDATE, QST_FAST,
  			USERS_NAME, QST_SPECIES, ANM_SPECIES, QST_PART, PART_NAME,
				ROW_NUMBER() OVER(ORDER BY QST_FAST DESC, QST_REGDATE DESC) rn
		 	FROM QUESTBOARD q JOIN USERSINFO u  
				ON q.QST_ID = u.USERS_ID 
				JOIN ANIMALCODE a1
				ON q.QST_SPECIES = a1.ANM_CODE
				JOIN ANIMALPART a2
				ON q.QST_PART = a2.PART_CODE 
  	WHERE QST_STATUS != 'N'
		)
	WHERE rn BETWEEN '1' AND '5';



  	SELECT 	QST_SEQ, QST_ID, QST_TITLE, QST_CONTENT, QST_STATUS, QST_REGDATE, QST_FAST,
  			USERS_NAME, QST_SPECIES, ANM_SPECIES, QST_PART, PART_NAME
	FROM (
		SELECT QST_SEQ, QST_ID, QST_TITLE, QST_CONTENT, QST_STATUS, QST_REGDATE, QST_FAST,
  			USERS_NAME, QST_SPECIES, ANM_SPECIES, QST_PART, PART_NAME,
				ROW_NUMBER() OVER(ORDER BY QST_FAST DESC, QST_REGDATE DESC) rn
		 	FROM QUESTBOARD q JOIN USERSINFO u  
				ON q.QST_ID = u.USERS_ID 
				JOIN ANIMALCODE a1
				ON q.QST_SPECIES = a1.ANM_CODE
				JOIN ANIMALPART a2
				ON q.QST_PART = a2.PART_CODE 
  	WHERE QST_STATUS != 'N'
  	AND qst_species = 'A'
  	AND qst_content LIKE '%'||''||'%'

		)
	WHERE rn BETWEEN '1' AND '5'
    ORDER BY QST_FAST DESC, QST_REGDATE DESC;
   
--   대분류/소분류 선택 전체조회
  	SELECT 	QST_SEQ, QST_ID, QST_TITLE, QST_CONTENT, QST_STATUS, QST_REGDATE, QST_FAST,
  			USERS_NAME, QST_SPECIES, ANM_SPECIES, QST_PART, PART_NAME
	FROM (
		SELECT QST_SEQ, QST_ID, QST_TITLE, QST_CONTENT, QST_STATUS, QST_REGDATE, QST_FAST,
  			USERS_NAME, QST_SPECIES, ANM_SPECIES, QST_PART, PART_NAME,
				ROW_NUMBER() OVER(ORDER BY QST_FAST DESC, QST_REGDATE DESC) rn
		 	FROM QUESTBOARD q JOIN USERSINFO u  
				ON q.QST_ID = u.USERS_ID 
				JOIN ANIMALCODE a1
				ON q.QST_SPECIES = a1.ANM_CODE
				JOIN ANIMALPART a2
				ON q.QST_PART = a2.PART_CODE 
  	WHERE QST_STATUS != 'N'
		)
	WHERE qst_species = 'A'
	AND qst_part = '01'
	AND qst_content LIKE '%'||'강아지'||'%'
	AND rn BETWEEN '1' AND '5'
    ORDER BY QST_FAST DESC, QST_REGDATE DESC;

   