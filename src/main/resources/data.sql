delete from t_user;
delete from t_movie;
delete from t_comment;

insert into t_user(username, password, phone, level, profileURL, approvalNum)
values ('张三','123456','10086','充话费送的会员','/profile',3),
       ('李四','123456','123456789','普通会员','/profile',6);

insert into t_movie(name, classification, durationM, introduction, imageDirectory, movieURL, level, score, scoreNum)
values ('环太平洋','科幻',131,'近未来，地球环境逐步恶化。','/directory','/url','普通电影',7.6,13),
       ('肖申克的救赎','剧情',142,'20世纪40年代末，小有成就的青年银行家安迪因涉嫌杀害妻子及她的情人而锒铛入狱。',
        '/directory','/directory','会员电影',9.7,1200);