package nccloud.impl.sync;

import nc.utils.encode.SignUtils;

public class TestSign {

		public static void main(String[] args) {
			String str = "bizType=01&endDate=2021-07-24 00:00:00&mchNo=212590026127&signType=RSA_1_256&startDate=2021-07-01 00:00:00";
			
			String key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKIKk2ELLQI4GFnbURuNPz5qG911hS8trWu4bThVCORO3VZwDGoNW2G2c5Df9dIWFlSe4+2syPHCMFpF3Whbsn5rg7DcfAy3yCNI8I58/YnLUx+l64tDV1qBRlW0uvEXldtxNvVCiNttyEqGYxvPPPIgKgd8vMHjg6ssCRiarw8BAgMBAAECgYAMUrGSl778hDeV3c/1QHCm1nr4BI/0XozpvETSG+VpN3YttKe9CPcCH1X6lo2jRHq+RLcgxBskB5iVxOppnU+dnp30JMXm2mScKX88OLmmnJHqMBCyn5uj9DaCk4hxH6gKLVLcZ8NjKO+lc2ebfz9I+uAAcggiouGJob0rySWbYQJBANR4VsNU4bJxy0IRDXA/nOmvLiA5ok2NSbMmRuB4saWjU97Tz07eS6LDNt2SR5Fq/K/4PuuequIT2JiBaT0yfusCQQDDPVd+Ig0CSHJsMc3klN4uCyWbU0LvTclQQjneyZxOjIdL3LC0zpJyh2twQrV7UIJFaKzuXYq6i4hxfE55xKbDAkBBYL4h4M6KICXRMHoYHhrkdTkWRWXBw0X4UEDi9YCEXVuDZ12VASX48WTCjS5keq1CYHrL+cjPRBgT5U5CvHT9AkBh13zgNhpE2Z+V/wOKmNwbQPTQuVIi8N2BPh8YOfr+Fki7SNa5VYhootrBwHyIVtliBUocjn42bDStgrklJtrXAkEAkrM4lNbBy4CmLMdNT9BaIfupZs+8xJeTle7ij80i3t/ULgzfTiVrJxRxk/oYDDVvHQujMI6q6L0OgvxrS9QMPg==";
			
			try {
				String rsaSign = SignUtils.rsaSign(str, "RSA_1_256", key);
				System.out.println(rsaSign);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
