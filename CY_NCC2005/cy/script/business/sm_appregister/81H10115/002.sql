INSERT INTO SM_APPREGISTER (APP_DESC, APPTYPE, CODE, CREATIONTIME, CREATOR, DR, FUN_PROPERTY, FUNTYPE, HEIGHT, HELP_NAME, IMAGE_SRC, ISCAUSERUSABLE, ISCOPYPAGE, ISENABLE, MDID, MOUNTID, NAME, ORGTYPECODE, OWN_MODULE, PARENT_ID, PK_APPREGISTER, PK_GROUP, SOURCE_APP_CODE, TARGET_PATH, USELICENSE_LOAD, WIDTH, TS)
VALUES (null, 1, '81H10115', '2021-06-23 09:36:36', '~', 0, 0, 0, 1, null, 'yewuchulilei2', 'N', 'N', 'Y', '9db2e2be-2abc-415f-bb42-6736d49e8249', null, '词源班级档案', 'GLOBLE00000000000000', '81H1', '0001ZZZZZZ000081H101', '0001ZZZZb7f546ce9259', '~', '81H10115', null, 'Y', 1, '2021-06-23 09:36:36');
go

INSERT INTO SM_APPREGISTER (APP_DESC, APPTYPE, CODE, CREATIONTIME, CREATOR, DR, FUN_PROPERTY, FUNTYPE, HEIGHT, HELP_NAME, IMAGE_SRC, ISCAUSERUSABLE, ISCOPYPAGE, ISENABLE, MDID, MOUNTID, NAME, ORGTYPECODE, OWN_MODULE, PARENT_ID, PK_APPREGISTER, PK_GROUP, SOURCE_APP_CODE, TARGET_PATH, USELICENSE_LOAD, WIDTH, TS)
VALUES (null, 1, '81H10115A', '2021-06-23 09:36:36', '~', 0, 1, 0, 1, null, 'yewuchulilei3', 'N', 'N', 'Y', '9db2e2be-2abc-415f-bb42-6736d49e8249', null, '词源班级档案审批', 'GLOBLE00000000000000', '81H1', '0001ZZZZZZ000081H101', '0001ZZZZ2a7f263b583e', '~', '81H10115A', '0001ZZZZ37eb23169e70', 'Y', 1, '2021-06-23 09:36:36');
go


INSERT INTO SM_APPPAGE (CREATIONTIME, CREATOR, DR, ISCARDDEFAULT, ISDEFAULT, MODIFIEDTIME, MODIFIER, PAGECODE, PAGEDESC, PAGENAME, PAGEURL, PARENT_ID, PARENTCODE, PK_APPPAGE, TS) VALUES (null, '~', 0, 'N', 'Y', null, '~', '81H10115_LIST', '词源班级档案列表页', '词源班级档案列表', '/nccloud/resources/cy/cy/81h10115/main/index.html#/list', '0001ZZZZb7f546ce9259', '81H10115', '0001ZZZZ09e1afd67adc', '2021-06-23 09:36:36');
go

INSERT INTO SM_APPPAGE (CREATIONTIME, CREATOR, DR, ISCARDDEFAULT, ISDEFAULT, MODIFIEDTIME, MODIFIER, PAGECODE, PAGEDESC, PAGENAME, PAGEURL, PARENT_ID, PARENTCODE, PK_APPPAGE, TS) VALUES (null, '~', 0, 'N', 'N', null, '~', '81H10115_CARD', '词源班级档案卡片页', '词源班级档案卡片', '/nccloud/resources/cy/cy/81h10115/main/index.html#/card', '0001ZZZZb7f546ce9259', '81H10115', '0001ZZZZ1d22c7b5abaa', '2021-06-23 09:36:36');
go

INSERT INTO SM_APPPAGE (CREATIONTIME, CREATOR, DR, ISCARDDEFAULT, ISDEFAULT, MODIFIEDTIME, MODIFIER, PAGECODE, PAGEDESC, PAGENAME, PAGEURL, PARENT_ID, PARENTCODE, PK_APPPAGE, TS) VALUES (null, '~', 0, 'N', 'N', null, '~', '81H10115L_CARD', '词源班级档案联查卡片页', '词源班级档案卡片联查', '/nccloud/resources/cy/cy/81h10115/main/index.html#/card', '0001ZZZZb7f546ce9259', '81H10115', '0001ZZZZd2b904f23d9d', '2021-06-23 09:36:36');
go


INSERT INTO SM_APPPAGE (CREATIONTIME, CREATOR, DR, ISCARDDEFAULT, ISDEFAULT, MODIFIEDTIME, MODIFIER, PAGECODE, PAGEDESC, PAGENAME, PAGEURL, PARENT_ID, PARENTCODE, PK_APPPAGE, TS) VALUES (null, '~', 0, 'N', 'Y', null, '~', '81H10115A_CARD', '词源班级档案审批', '词源班级档案审批', '/nccloud/resources/cy/cy/81h10115/main/index.html#/card', '0001ZZZZ2a7f263b583e', '81H10115A', '0001ZZZZ37eb23169e70', '2021-06-23 09:36:36');
go


INSERT INTO SM_APPMENUITEM (APPCODE, APPID, DR, MENUDES, MENUITEMCODE, MENUITEMNAME, PARENTCODE, PK_GROUP, PK_MENU, PK_MENUITEM, TS) VALUES ('81H10115', '0001ZZZZb7f546ce9259', 0, null, '81H10115', 'cy', '81H101', '~', '1004Z510000000000FFL', '0001ZZZZd79ec123eb52', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPMENUITEM (APPCODE, APPID, DR, MENUDES, MENUITEMCODE, MENUITEMNAME, PARENTCODE, PK_GROUP, PK_MENU, PK_MENUITEM, TS) VALUES ('81H10115A', '0001ZZZZ2a7f263b583e', 0, null, '81H10115A', 'cy审批', '81H101', '~', '1004Z510000000000FFL', '0001ZZZZdaef8cfe0b34', '2021-06-23 09:36:36');
go



INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZ2a7f263b583e', 'card_head', 'AttachmentBtn', 'button_secondary', null, '附件', 0, 'general_btn', 0, 'Y', 'N', null, null, '81H10115A_CARD', null, '0001ZZZZ02c259fa09db', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZ2a7f263b583e', 'card_head', 'JointBtn', 'button_secondary', null, '联查', 1, 'dropdown', 0, 'Y', 'N', null, null, '81H10115A_CARD', null, '0001ZZZZ5605b36e6486', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZ2a7f263b583e', 'card_head', 'JointGroup', 'button_secondary', null, null, 2, 'general_btn', 0, 'Y', 'N', null, null, '81H10115A_CARD', 'JointBtn', '0001ZZZZ491ef4dad008', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZ2a7f263b583e', 'card_head', 'DetailBtn', 'button_secondary', null, '审批详情', 3, 'general_btn', 0, 'Y', 'N', null, null, '81H10115A_CARD', 'JointGroup', '0001ZZZZ101c15fc374c', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZ2a7f263b583e', 'card_head', 'TrackBtn', 'button_secondary', null, '单据追溯', 4, 'general_btn', 0, 'Y', 'N', null, null, '81H10115A_CARD', 'JointGroup', '0001ZZZZ48934e70d748', '2021-06-23 09:36:36');
go



INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'AttachmentBtn', 'button_secondary', null, '附件', 0, 'general_btn', 0, 'Y', 'N', null, null, '81H10115L_CARD', null, '0001ZZZZf863ce1d0420', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'JointBtn', 'button_secondary', null, '联查', 1, 'dropdown', 0, 'Y', 'N', null, null, '81H10115L_CARD', null, '0001ZZZZ6b57eda73fa4', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'JointGroup', 'button_secondary', null, null, 2, 'general_btn', 0, 'Y', 'N', null, null, '81H10115L_CARD', 'JointBtn', '0001ZZZZ80bd5f248853', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'DetailBtn', 'button_secondary', null, '审批详情', 3, 'general_btn', 0, 'Y', 'N', null, null, '81H10115L_CARD', 'JointGroup', '0001ZZZZe337536c3be6', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'TrackBtn', 'button_secondary', null, '单据追溯', 4, 'general_btn', 0, 'Y', 'N', null, null, '81H10115L_CARD', 'JointGroup', '0001ZZZZ764742dad70d', '2021-06-23 09:36:36');
go

INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'SaveGroup', 'button_secondary', null, null, 0, 'buttongroup', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZ9afeee1a6716', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'SaveBtn', 'button_main', null, '保存', 1, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', 'SaveGroup', '0001ZZZZ6d9bc46e8eac', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'SaveAddBtn', 'button_secondary', null, '保存新增', 2, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', 'SaveGroup', '0001ZZZZd2bfb2ce2550', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'SaveCommitBtn', 'button_secondary', null, '保存提交', 3, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', 'SaveGroup', '0001ZZZZ7f90e39eb81e', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'CancelBtn', 'button_secondary', null, '取消', 4, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZ9d5aec26184d', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'CUDGroup', 'button_secondary', null, null, 5, 'buttongroup', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZab0ea3ff616e', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'CreateBtn', 'button_main', null, '新增', 6, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', 'CUDGroup', '0001ZZZZ64c2a99cbf4a', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'UpdateBtn', 'button_secondary', null, '修改', 7, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', 'CUDGroup', '0001ZZZZf09216147ac7', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'DeleteBtn', 'button_secondary', null, '删除', 8, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', 'CUDGroup', '0001ZZZZb570f67f31da', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'CommitBtn', 'button_main', null, '提交', 9, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZ18c0499eb389', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'UnCommitBtn', 'button_main', null, '收回', 10, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZ9e8e0e16c90f', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'CopyBtn', 'button_secondary', null, '复制', 11, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZf256e015faf4', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'AttachmentBtn', 'button_secondary', null, '附件', 12, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZ0267996ef2d7', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'JointBtn', 'button_secondary', null, '联查', 13, 'dropdown', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZ5e6be8c8c3cc', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'JointGroup', 'button_secondary', null, null, 14, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', 'JointBtn', '0001ZZZZ0df498e4671d', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'DetailBtn', 'button_secondary', null, '审批详情', 15, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', 'JointGroup', '0001ZZZZed578e0fa72d', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'TrackBtn', 'button_secondary', null, '单据追溯', 16, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', 'JointGroup', '0001ZZZZ0c30bbe8a8fb', '2021-06-23 09:36:36');
go

INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'card_head', 'RefreshBtn', 'button_secondary', null, null, 20, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZdde453a2a996', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'tabs_head', 'tabGroup', 'button_secondary', null, null, 21, 'buttongroup', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZ097f1a9f8aa0', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'tabs_head', 'addRow', 'button_secondary', null, '增行', 22, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', 'tabGroup', '0001ZZZZ8c996d95a099', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'tabs_head', 'deleteRow', 'button_secondary', null, '删行', 23, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', 'tabGroup', '0001ZZZZeac9c28be165', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'tabs_head', 'copyRows', 'button_secondary', null, '复制行', 24, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZ1856504d4041', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'tabs_head', 'pasteTail', 'button_secondary', null, '粘贴至末行', 25, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZd15282f34e67', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'tabs_head', 'pasteCancel', 'button_secondary', null, '取消粘贴', 26, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZf2546458b8a9', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'tabs_body', 'expand', 'button_secondary', null, '展开', 27, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZf703e065ec59', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'tabs_body', 'fold', 'button_secondary', null, '收起', 28, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZ8460de750e1a', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'tabs_body', 'unfold', 'button_secondary', null, '展开', 29, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZ82b7c76b3630', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'tabs_body', 'insertRow', 'button_secondary', null, '插行', 30, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZc4b77f34733b', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'tabs_body', 'delRow', 'button_secondary', null, '删行', 31, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZ46f6f1a0f221', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'tabs_body', 'copyRow', 'button_secondary', null, '复制', 32, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZ8fb18da8abce', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'tabs_body', 'pasteHere', 'button_secondary', null, '粘贴至此', 33, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_CARD', null, '0001ZZZZcde28f1d183f', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'AddDelGroup', 'button_secondary', null, null, 0, 'buttongroup', 0, 'Y', 'N', null, null, '81H10115_LIST', null, '0001ZZZZ2622282d1dfa', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'AddBtn', 'button_main', null, '新增', 1, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', 'AddDelGroup', '0001ZZZZee8a093aebbc', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'DelBtn', 'button_secondary', null, '删除', 2, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', 'AddDelGroup', '0001ZZZZ4ce141abc6f5', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'CommitBtn', 'button_secondary', null, '提交', 3, 'divider', 0, 'Y', 'N', null, null, '81H10115_LIST', null, '0001ZZZZc19fe1713ad0', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'CommitGroup', 'button_secondary', null, null, 4, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', 'CommitBtn', '0001ZZZZfcac51a014d8', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'UnCommitBtn', 'button_secondary', null, '收回', 5, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', 'CommitGroup', '0001ZZZZ666da7523114', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'AttachmentBtn', 'button_secondary', null, '附件', 6, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', null, '0001ZZZZda7dc94c806e', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'JointBtn', 'button_secondary', null, '联查', 7, 'dropdown', 0, 'Y', 'N', null, null, '81H10115_LIST', null, '0001ZZZZ93f75a467e05', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'JointGroup', 'button_secondary', null, null, 8, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', 'JointBtn', '0001ZZZZe2b54a170a44', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'DetailBtn', 'button_secondary', null, '审批详情', 9, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', 'JointGroup', '0001ZZZZ71ee30e328d0', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'TrackBtn', 'button_secondary', null, '单据追溯', 10, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', 'JointGroup', '0001ZZZZ72d8d9d8e35d', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'PrintBtn', 'button_secondary', null, '打印', 11, 'divider', 0, 'Y', 'N', null, null, '81H10115_LIST', null, '0001ZZZZ71e445cf5d54', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'PrintGroup', 'button_secondary', null, null, 12, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', 'PrintBtn', '0001ZZZZ26d77c6f58bc', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'OutputBtn', 'button_secondary', null, '输出', 13, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', 'PrintGroup', '0001ZZZZ14a397245900', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_head', 'RefreshBtn', 'button_secondary', null, null, 14, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', null, '0001ZZZZ86f82d54dc79', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_inner', 'edit', 'button_secondary', null, '修改', 15, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', null, '0001ZZZZ63966d84c11a', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_inner', 'commit', 'button_secondary', null, '提交', 16, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', null, '0001ZZZZe731e259326b', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_inner', 'delete', 'button_secondary', null, '删除', 17, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', null, '0001ZZZZf4b6ef8ef049', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_inner', 'unCommit', 'button_secondary', null, '收回', 18, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', null, '0001ZZZZcc95e8aa80e4', '2021-06-23 09:36:36');
go
INSERT INTO SM_APPBUTNREGISTER (APPID, BTNAREA, BTNCODE, BTNCOLOR, BTNDESC, BTNNAME, BTNORDER, BTNTYPE, DR, ISENABLE, ISKEYFUNC, KEYBOARD, PAGEAREACODE, PAGECODE, PARENT_CODE, PK_BTN, TS) VALUES ('0001ZZZZb7f546ce9259', 'list_inner', 'copy', 'button_secondary', null, '复制', 19, 'general_btn', 0, 'Y', 'N', null, null, '81H10115_LIST', null, '0001ZZZZ8ff606c1dcca', '2021-06-23 09:36:36');
go







