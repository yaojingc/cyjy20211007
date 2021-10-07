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
                '0001ZZZZABDE028F3BF5',
                NULL,
                '~',
                NULL,
                '学费收款合同',
                'Y',
                NULL,
                NULL,
                NULL,
                'creditcontract',
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
                '81H11505',
                '~',
                'SKHT',
                '~',
                'GLOBLE00000000000000',
                NULL,
                'CY',
                NULL,
                '2021-06-23 10:38:23',
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
                '0001ZZZZ7056D38DFFE6',
                11,
                '~',
                '2',
                NULL,
                'APPROVE',
                'N',
                'N',
                NULL,
                'N',
                'SKHT',
                '0001ZZZZABDE028F3BF5',
                NULL,
                '~',
                '2021-06-23 10:38:23'
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
                '0001ZZZZ4F33945109D3',
                30,
                '~',
                '3',
                NULL,
                'DELETE',
                'N',
                'N',
                NULL,
                'N',
                'SKHT',
                '0001ZZZZABDE028F3BF5',
                NULL,
                '~',
                '2021-06-23 10:38:23'
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
                '0001ZZZZ286C3FC3A053',
                10,
                '~',
                '1',
                NULL,
                'SAVE',
                'N',
                'N',
                NULL,
                'N',
                'SKHT',
                '0001ZZZZABDE028F3BF5',
                NULL,
                '~',
                '2021-06-23 10:38:23'
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
                '0001ZZZZ71896CE28A72',
                31,
                '~',
                '1',
                NULL,
                'SAVEBASE',
                'Y',
                'N',
                NULL,
                'N',
                'SKHT',
                '0001ZZZZABDE028F3BF5',
                NULL,
                '~',
                '2021-06-23 10:38:23'
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
                '0001ZZZZAFF8B15A5050',
                12,
                '~',
                '3',
                NULL,
                'UNAPPROVE',
                'N',
                'N',
                NULL,
                'Y',
                'SKHT',
                '0001ZZZZABDE028F3BF5',
                NULL,
                '~',
                '2021-06-23 10:38:23'
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
                '0001ZZZZ088E1ACA966C',
                13,
                '~',
                '3',
                NULL,
                'UNSAVEBILL',
                'N',
                'Y',
                NULL,
                'Y',
                'SKHT',
                '0001ZZZZABDE028F3BF5',
                NULL,
                '~',
                '2021-06-23 10:38:23'
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
                '0001ZZZZ35D351A43B00',
                'SAVE',
                'N_SKHT_SAVE',
                0,
                'N',
                'SKHT',
                '0001ZZZZABDE028F3BF5',
                '~',
                '~',
                '2021-06-23 10:38:23'
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
                '0001ZZZZ93DC9C132FB7',
                'APPROVE',
                'N_SKHT_APPROVE',
                0,
                'N',
                'SKHT',
                '0001ZZZZABDE028F3BF5',
                '~',
                '~',
                '2021-06-23 10:38:23'
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
                '0001ZZZZ9EE10CFB8960',
                'UNSAVEBILL',
                'N_SKHT_UNSAVEBILL',
                0,
                'N',
                'SKHT',
                '0001ZZZZABDE028F3BF5',
                '~',
                '~',
                '2021-06-23 10:38:23'
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
                '0001ZZZZFE161CE1F2CF',
                'UNAPPROVE',
                'N_SKHT_UNAPPROVE',
                0,
                'N',
                'SKHT',
                '0001ZZZZABDE028F3BF5',
                '~',
                '~',
                '2021-06-23 10:38:23'
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
                '0001ZZZZD35E32C9AD1E',
                'DELETE',
                'N_SKHT_DELETE',
                0,
                'N',
                'SKHT',
                '0001ZZZZABDE028F3BF5',
                '~',
                '~',
                '2021-06-23 10:38:23'
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
                '0001ZZZZEA731C8A1CE7',
                'SAVEBASE',
                'N_SKHT_SAVEBASE',
                0,
                'N',
                'SKHT',
                '0001ZZZZABDE028F3BF5',
                '~',
                '~',
                '2021-06-23 10:38:23'
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
        '0001ZZZZ4F843A4B1337',
        0,
        'SKHTT',
        null,
        null,
        '0001ZZZZABDE028F3BF5',
        null,
        '2021-06-23 10:38:23',
        'Y',
        'Y'
);
go
