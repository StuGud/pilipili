# delete from t_user;
# delete from t_movie;
# delete from t_comment;
#
# insert into t_user(id,username, password, mail, level, profileURL, approvalNum)
# values (0,'张三','123456','10086','充话费送的会员','profile1.jpg',3),
#        (1,'李四','123456','123456789','普通会员','profile2.jpg',6),
#        (2,'王五','123456','741498908@qq.com','超级vip','/directory',6);
#
# insert into t_movie(id,name, classification, durationM, introduction, imageDirectory, movieURL, level, score, scoreNum)
# values (0,'环太平洋','科幻',131,'近未来，地球环境逐步恶化。','Pacific_Rim_1.jpg','/url','普通电影',7.6,13),
#        (1,'肖申克的救赎','剧情',142,'20世纪40年代末，小有成就的青年银行家安迪因涉嫌杀害妻子及她的情人而锒铛入狱。',
#         'The_Shawshank_Redemption_1.jpg','/directory','会员电影',9.7,1200);
#
# insert into t_comment(id, userId, movieId, score, content, createdAt, approvalNum)
# values (1,1,1,9.0,'第1号评论：user1对movie1的评论','2020-9-1',666),
#        (2,2,2,9.9,'第2号评论：user2对movie2的评论','2020-9-2',233);