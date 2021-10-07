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
                '0001ZZZZ8CA061A74C02',
                'LSSQ',
                40,
                'global',
                'after',
                NULL,
                0,
                '591516f3-f509-4d71-ad7c-311c8ce01e8a',
                '老师申请单',
                'GLOBLE00000000000000',
                '2021-07-06 09:14:25'
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
                '0001ZZZZACFAF3FCA2E7',
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
                'LSSQ',
                'GLOBLE00000000000000',
                'LSSQ',
                '老师申请单',
                '2021-07-06 09:14:25'
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
                '0001ZZZZDFAABA668164',
                NULL,
                0,
                4,
                0,
                'LSSQ',
                0,
                NULL,
                0,
                0,
                '0001ZZZZACFAF3FCA2E7',
                '~',
                '2021-07-06 09:14:25'
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
                '0001ZZZZD7083182CDFC',
                NULL,
                0,
                8,
                2,
                'billdate',
                1,
                NULL,
                0,
                0,
                '0001ZZZZACFAF3FCA2E7',
                '~',
                '2021-07-06 09:14:25'
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
                '0001ZZZZ66C813DB9EB5',
                NULL,
                0,
                8,
                3,
                'nc.pub.billcode.BillCodePureDigitalSNGenerator',
                2,
                NULL,
                0,
                0,
                '0001ZZZZACFAF3FCA2E7',
                '~',
                '2021-07-06 09:14:25'
        );
go