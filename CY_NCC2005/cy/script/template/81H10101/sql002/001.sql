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
                '0001ZZZZ16F7F90AD009',
                NULL,
                '~',
                NULL,
                '学校档案',
                'Y',
                NULL,
                NULL,
                NULL,
                'schoolarchives',
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
                '81H10101',
                '~',
                'XXDA',
                '~',
                'GLOBLE00000000000000',
                NULL,
                'CY',
                NULL,
                '2021-07-02 14:38:31',
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
                '0001ZZZZ3CF4976126BE',
                11,
                '~',
                '2',
                NULL,
                'APPROVE',
                'N',
                'N',
                NULL,
                'N',
                'XXDA',
                '0001ZZZZ16F7F90AD009',
                NULL,
                '~',
                '2021-07-02 14:38:31'
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
                '0001ZZZZCDE8C5FDC70F',
                30,
                '~',
                '3',
                NULL,
                'DELETE',
                'N',
                'N',
                NULL,
                'N',
                'XXDA',
                '0001ZZZZ16F7F90AD009',
                NULL,
                '~',
                '2021-07-02 14:38:31'
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
                '0001ZZZZ84130750BD29',
                10,
                '~',
                '1',
                NULL,
                'SAVE',
                'N',
                'N',
                NULL,
                'N',
                'XXDA',
                '0001ZZZZ16F7F90AD009',
                NULL,
                '~',
                '2021-07-02 14:38:31'
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
                '0001ZZZZ48E1880A53B5',
                31,
                '~',
                '1',
                NULL,
                'SAVEBASE',
                'Y',
                'N',
                NULL,
                'N',
                'XXDA',
                '0001ZZZZ16F7F90AD009',
                NULL,
                '~',
                '2021-07-02 14:38:31'
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
                '0001ZZZZD8A804F25BDD',
                12,
                '~',
                '3',
                NULL,
                'UNAPPROVE',
                'N',
                'N',
                NULL,
                'Y',
                'XXDA',
                '0001ZZZZ16F7F90AD009',
                NULL,
                '~',
                '2021-07-02 14:38:31'
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
                '0001ZZZZF1301D5389BC',
                13,
                '~',
                '3',
                NULL,
                'UNSAVEBILL',
                'N',
                'Y',
                NULL,
                'Y',
                'XXDA',
                '0001ZZZZ16F7F90AD009',
                NULL,
                '~',
                '2021-07-02 14:38:31'
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
                '0001ZZZZBF35192294A3',
                'SAVE',
                'N_XXDA_SAVE',
                0,
                'N',
                'XXDA',
                '0001ZZZZ16F7F90AD009',
                '~',
                '~',
                '2021-07-02 14:38:31'
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
                '0001ZZZZCCAF962A35BC',
                'APPROVE',
                'N_XXDA_APPROVE',
                0,
                'N',
                'XXDA',
                '0001ZZZZ16F7F90AD009',
                '~',
                '~',
                '2021-07-02 14:38:31'
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
                '0001ZZZZ853BB73B5B9E',
                'UNSAVEBILL',
                'N_XXDA_UNSAVEBILL',
                0,
                'N',
                'XXDA',
                '0001ZZZZ16F7F90AD009',
                '~',
                '~',
                '2021-07-02 14:38:31'
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
                '0001ZZZZ546CD07FB1EF',
                'UNAPPROVE',
                'N_XXDA_UNAPPROVE',
                0,
                'N',
                'XXDA',
                '0001ZZZZ16F7F90AD009',
                '~',
                '~',
                '2021-07-02 14:38:31'
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
                '0001ZZZZAAD8BF3BC9F5',
                'DELETE',
                'N_XXDA_DELETE',
                0,
                'N',
                'XXDA',
                '0001ZZZZ16F7F90AD009',
                '~',
                '~',
                '2021-07-02 14:38:31'
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
                '0001ZZZZ8BEB66FFF2A4',
                'SAVEBASE',
                'N_XXDA_SAVEBASE',
                0,
                'N',
                'XXDA',
                '0001ZZZZ16F7F90AD009',
                '~',
                '~',
                '2021-07-02 14:38:31'
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
        '0001ZZZZ08ECA03C9E7C',
        0,
        'XXDAT',
        null,
        null,
        '0001ZZZZ16F7F90AD009',
        null,
        '2021-07-02 14:38:31',
        'Y',
        'Y'
);
go
