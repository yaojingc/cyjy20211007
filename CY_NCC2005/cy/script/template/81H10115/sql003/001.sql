INSERT INTO pub_bcr_nbcr (
        pk_nbcr,
        code,
        codelenth,
        codescope,
        codestyle,
        comp,
        dr,
        metaid,
        name,
        orgtype,
        ts
)
VALUES
        (
                '0001ZZZZ777C92DAD094',
                'CYBJ',
                40,
                'global',
                'after',
                NULL,
                0,
                '9db2e2be-2abc-415f-bb42-6736d49e8249',
                '词源班级档案',
                'GLOBLE00000000000000',
                '2021-06-23 09:36:37'
        );

go

INSERT INTO pub_bcr_rulebase (
        pk_billcodebase,
        codemode,
        codescope,
        dataoriginflag,
        dr,
        format,
        isautofill,
        isdefault,
        iseditable,
        isgetpk,
        islenvar,
        isused,
        nbcrcode,
        pk_group,
        rulecode,
        rulename,
        ts
)
VALUES
        (
                '0001ZZZZ0789562C82FD',
                'after',
                'g',
                0,
                0,
                'yyyyMMdd',
                'Y',
                'N',
                'N',
                'N',
                'Y',
                'Y',
                'CYBJ',
                'GLOBLE00000000000000',
                'CYBJ',
                '词源班级档案',
                '2021-06-23 09:36:37'
        );

go

INSERT INTO pub_bcr_elem (
        pk_billcodeelem,
        dataoriginflag,
        dr,
        elemlenth,
        elemtype,
        elemvalue,
        eorder,
        fillsign,
        fillstyle,
        isrefer,
        pk_billcodebase,
        pk_billcodeentity,
        ts
)
VALUES
        (
                '0001ZZZZ5FAFA453EDFB',
                NULL,
                0,
                4,
                0,
                'CYBJ',
                0,
                NULL,
                0,
                0,
                '0001ZZZZ0789562C82FD',
                '~',
                '2021-06-23 09:36:37'
        );
go
INSERT INTO pub_bcr_elem (
        pk_billcodeelem,
        dataoriginflag,
        dr,
        elemlenth,
        elemtype,
        elemvalue,
        eorder,
        fillsign,
        fillstyle,
        isrefer,
        pk_billcodebase,
        pk_billcodeentity,
        ts
)
VALUES
        (
                '0001ZZZZ1462FC601C8C',
                NULL,
                0,
                8,
                2,
                'billdate',
                1,
                NULL,
                0,
                0,
                '0001ZZZZ0789562C82FD',
                '~',
                '2021-06-23 09:36:37'
        );
go
INSERT INTO pub_bcr_elem (
        pk_billcodeelem,
        dataoriginflag,
        dr,
        elemlenth,
        elemtype,
        elemvalue,
        eorder,
        fillsign,
        fillstyle,
        isrefer,
        pk_billcodebase,
        pk_billcodeentity,
        ts
)
VALUES
        (
                '0001ZZZZA61FD77E9AA1',
                NULL,
                0,
                8,
                3,
                'nc.pub.billcode.BillCodePureDigitalSNGenerator',
                2,
                NULL,
                0,
                0,
                '0001ZZZZ0789562C82FD',
                '~',
                '2021-06-23 09:36:37'
        );
go