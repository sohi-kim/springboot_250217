-- 파일 업로드 테스트 테이블
-- 
CREATE TABLE tbl_gallery (
	seq number(6) PRIMARY KEY ,
	title varchar2(200),
	writer varchar2(200),
	filenames varchar2(4000),
	reg_date DATE DEFAULT sysdate
);

CREATE SEQUENCE gallery_seq ;

INSERT INTO tbl_gallery (seq,title,writer,filenames)
	VALUES (gallery_seq.nextval,'침실','iclass','침실1.jpg,침실2.jpg,침실3.jpg,침실4.jpg');
INSERT INTO tbl_gallery (seq,title,writer,filenames)
	VALUES (gallery_seq.nextval,'거실','iclass','거실1.jpg,거실2.jpg');
INSERT INTO tbl_gallery (seq,title,writer,filenames)
	VALUES (gallery_seq.nextval,'키친','iclass','키친1.jpg,키친2.jpg,키친3.jpg');
	
SELECT * FROM tbl_gallery ;