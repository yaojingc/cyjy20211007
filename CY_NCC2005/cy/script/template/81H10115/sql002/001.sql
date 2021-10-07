INSERT INTO bd_billtype (
        pk_billtypeid,
        accountclass,
        billcoderule,
        billstyle,
        billtypename,
        canextendtransaction,
        checkclassname,
        classname,
        comp,
        component,
        datafinderclz,
        def1,
        def2,
        def3,
        dr,
        emendenumclass,
        forwardbilltype,
        isaccount,
        isapprovebill,
        isbizflowbill,
        iseditableproperty,
        isenablebutton,
        isenabletranstypebcr,
        islightbill,
        islock,
        isrejectuneditableproperty,
        isroot,
        issupportmobile,
        istransaction,
        isworkflowcanautoapprove,
        ncbrcode,
        nodecode,
        parentbilltype,
        pk_billtypecode,
        pk_group,
        pk_org,
        referclassname,
        systemcode,
        transtype_class,
        ts,
        webnodecode,
        wherestring
)
VALUES
        (
                '0001ZZZZB9111652A2B7',
                NULL,
                '~',
                NULL,
                '词源班级档案',
                'Y',
                NULL,
                NULL,
                NULL,
                'classfilecy',
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                'Y',
                'Y',
                'N',
                'N',
                'N',
                'N',
                'Y',
                NULL,
                NULL,
                'N',
                'Y',
                'N',
                'N',
                '~',
                '81H10115',
                '~',
                'CYBJ',
                '~',
                'GLOBLE00000000000000',
                NULL,
                'CY',
                NULL,
                '2021-06-23 09:36:37',
                '~',
                NULL
        );

go

INSERT INTO pub_billaction (
        pk_billaction,
        action_type,
        actionnote,
        actionstyle,
        actionstyleremark,
        actiontype,
        constrictflag,
        controlflag,
        dr,
        finishflag,
        pk_billtype,
        pk_billtypeid,
        pushflag,
        showhint,
        ts
)
VALUES
        (
                '0001ZZZZ59356A91772E',
                11,
                '~',
                '2',
                NULL,
                'APPROVE',
                'N',
                'N',
                NULL,
                'N',
                'CYBJ',
                '0001ZZZZB9111652A2B7',
                NULL,
                '~',
                '2021-06-23 09:36:37'
        );
go

INSERT INTO pub_billaction (
        pk_billaction,
        action_type,
        actionnote,
        actionstyle,
        actionstyleremark,
        actiontype,
        constrictflag,
        controlflag,
        dr,
        finishflag,
        pk_billtype,
        pk_billtypeid,
        pushflag,
        showhint,
        ts
)
VALUES
        (
                '0001ZZZZ4A348F19B084',
                30,
                '~',
                '3',
                NULL,
                'DELETE',
                'N',
                'N',
                NULL,
                'N',
                'CYBJ',
                '0001ZZZZB9111652A2B7',
                NULL,
                '~',
                '2021-06-23 09:36:37'
        );

go

INSERT INTO pub_billaction (
        pk_billaction,
        action_type,
        actionnote,
        actionstyle,
        actionstyleremark,
        actiontype,
        constrictflag,
        controlflag,
        dr,
        finishflag,
        pk_billtype,
        pk_billtypeid,
        pushflag,
        showhint,
        ts
)
VALUES
        (
                '0001ZZZZE4A2B064E18D',
                10,
                '~',
                '1',
                NULL,
                'SAVE',
                'N',
                'N',
                NULL,
                'N',
                'CYBJ',
                '0001ZZZZB9111652A2B7',
                NULL,
                '~',
                '2021-06-23 09:36:37'
        );

go

INSERT INTO pub_billaction (
        pk_billaction,
        action_type,
        actionnote,
        actionstyle,
        actionstyleremark,
        actiontype,
        constrictflag,
        controlflag,
        dr,
        finishflag,
        pk_billtype,
        pk_billtypeid,
        pushflag,
        showhint,
        ts
)
VALUES
        (
                '0001ZZZZ9C54DF68875F',
                31,
                '~',
                '1',
                NULL,
                'SAVEBASE',
                'Y',
                'N',
                NULL,
                'N',
                'CYBJ',
                '0001ZZZZB9111652A2B7',
                NULL,
                '~',
                '2021-06-23 09:36:37'
        );

go

INSERT INTO PUB_BILLACTION (
        PK_BILLACTION,
        ACTION_TYPE,
        ACTIONNOTE,
        ACTIONSTYLE,
        ACTIONSTYLEREMARK,
        ACTIONTYPE,
        CONSTRICTFLAG,
        CONTROLFLAG,
        DR,
        FINISHFLAG,
        PK_BILLTYPE,
        PK_BILLTYPEID,
        PUSHFLAG,
        SHOWHINT,
        TS
)
VALUES
        (
                '0001ZZZZA1BAA5FFB40B',
                12,
                '~',
                '3',
                NULL,
                'UNAPPROVE',
                'N',
                'N',
                NULL,
                'Y',
                'CYBJ',
                '0001ZZZZB9111652A2B7',
                NULL,
                '~',
                '2021-06-23 09:36:37'
        );

go

INSERT INTO PUB_BILLACTION (
        PK_BILLACTION,
        ACTION_TYPE,
        ACTIONNOTE,
        ACTIONSTYLE,
        ACTIONSTYLEREMARK,
        ACTIONTYPE,
        CONSTRICTFLAG,
        CONTROLFLAG,
        DR,
        FINISHFLAG,
        PK_BILLTYPE,
        PK_BILLTYPEID,
        PUSHFLAG,
        SHOWHINT,
        TS
)
VALUES
        (
                '0001ZZZZC475A37BADB7',
                13,
                '~',
                '3',
                NULL,
                'UNSAVEBILL',
                'N',
                'Y',
                NULL,
                'Y',
                'CYBJ',
                '0001ZZZZB9111652A2B7',
                NULL,
                '~',
                '2021-06-23 09:36:37'
        );


go

INSERT INTO pub_busiclass (
        pk_busiclass,
        actiontype,
        classname,
        dr,
        isbefore,
        pk_billtype,
        pk_billtypeid,
        pk_businesstype,
        pk_group,
        ts
)
VALUES
        (
                '0001ZZZZ3296F03C6AA2',
                'SAVE',
                'N_CYBJ_SAVE',
                0,
                'N',
                'CYBJ',
                '0001ZZZZB9111652A2B7',
                '~',
                '~',
                '2021-06-23 09:36:37'
        );

go

INSERT INTO pub_busiclass (
        pk_busiclass,
        actiontype,
        classname,
        dr,
        isbefore,
        pk_billtype,
        pk_billtypeid,
        pk_businesstype,
        pk_group,
        ts
)
VALUES
        (
                '0001ZZZZCDC51EEF8E41',
                'APPROVE',
                'N_CYBJ_APPROVE',
                0,
                'N',
                'CYBJ',
                '0001ZZZZB9111652A2B7',
                '~',
                '~',
                '2021-06-23 09:36:37'
        );

go

INSERT INTO pub_busiclass (
        pk_busiclass,
        actiontype,
        classname,
        dr,
        isbefore,
        pk_billtype,
        pk_billtypeid,
        pk_businesstype,
        pk_group,
        ts
)
VALUES
        (
                '0001ZZZZED30571C3BC0',
                'UNSAVEBILL',
                'N_CYBJ_UNSAVEBILL',
                0,
                'N',
                'CYBJ',
                '0001ZZZZB9111652A2B7',
                '~',
                '~',
                '2021-06-23 09:36:37'
        );

go

INSERT INTO pub_busiclass (
        pk_busiclass,
        actiontype,
        classname,
        dr,
        isbefore,
        pk_billtype,
        pk_billtypeid,
        pk_businesstype,
        pk_group,
        ts
)
VALUES
        (
                '0001ZZZZCC9EF46B3333',
                'UNAPPROVE',
                'N_CYBJ_UNAPPROVE',
                0,
                'N',
                'CYBJ',
                '0001ZZZZB9111652A2B7',
                '~',
                '~',
                '2021-06-23 09:36:37'
        );

go

INSERT INTO pub_busiclass (
        pk_busiclass,
        actiontype,
        classname,
        dr,
        isbefore,
        pk_billtype,
        pk_billtypeid,
        pk_businesstype,
        pk_group,
        ts
)
VALUES
        (
                '0001ZZZZ31EE9A2D44E3',
                'DELETE',
                'N_CYBJ_DELETE',
                0,
                'N',
                'CYBJ',
                '0001ZZZZB9111652A2B7',
                '~',
                '~',
                '2021-06-23 09:36:37'
        );

go

INSERT INTO PUB_BUSICLASS (
        PK_BUSICLASS,
        ACTIONTYPE,
        CLASSNAME,
        DR,
        ISBEFORE,
        PK_BILLTYPE,
        PK_BILLTYPEID,
        PK_BUSINESSTYPE,
        PK_GROUP,
        TS
)
VALUES
        (
                '0001ZZZZ06BE6288F2D0',
                'SAVEBASE',
                'N_CYBJ_SAVEBASE',
                0,
                'N',
                'CYBJ',
                '0001ZZZZB9111652A2B7',
                '~',
                '~',
                '2021-06-23 09:36:37'
        );
go


insert into pub_workitemconfig (
        pk_workitemconfig,
        dr,
        item,
        itemindex,
        itemtype,
        pk_billtype,
        resourceid,
        ts,
        isdefault,
        ismp
)
values
(
        '0001ZZZZCC0C139992AD',
        0,
        'CYBJT',
        null,
        null,
        '0001ZZZZB9111652A2B7',
        null,
        '2021-06-23 09:36:37',
        'Y',
        'Y'
);
go
