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
                '0001ZZZZ801F4A115EFD',
                'YHLS',
                40,
                'global',
                'after',
                NULL,
                0,
                'd895f3c5-b8a9-4cea-8c18-413c6d06f813',
                '银行收款流水',
                'GLOBLE00000000000000',
                '2021-06-22 17:47:56'
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
                '0001ZZZZ5E4923F22BBA',
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
                'YHLS',
                'GLOBLE00000000000000',
                'YHLS',
                '银行收款流水',
                '2021-06-22 17:47:56'
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
                '0001ZZZZE008ECFD6987',
                NULL,
                0,
                4,
                0,
                'YHLS',
                0,
                NULL,
                0,
                0,
                '0001ZZZZ5E4923F22BBA',
                '~',
                '2021-06-22 17:47:56'
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
                '0001ZZZZAAA37AFC70D8',
                NULL,
                0,
                8,
                2,
                'billdate',
                1,
                NULL,
                0,
                0,
                '0001ZZZZ5E4923F22BBA',
                '~',
                '2021-06-22 17:47:56'
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
                '0001ZZZZ8D31294606A9',
                NULL,
                0,
                8,
                3,
                'nc.pub.billcode.BillCodePureDigitalSNGenerator',
                2,
                NULL,
                0,
                0,
                '0001ZZZZ5E4923F22BBA',
                '~',
                '2021-06-22 17:47:56'
        );
go