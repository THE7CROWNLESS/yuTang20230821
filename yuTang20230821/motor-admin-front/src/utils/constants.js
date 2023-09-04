export default {
  delivery: [
    {
      code: 'ZJS',
      id: '103',
      name: '宅急送',
      reg_mail_no: '^[a-zA-Z0-9]{10}$|^(42|16)[0-9]{8}$|^A[0-9]{12}'
    },
    {
      code: 'SF',
      id: '505',
      name: '顺丰速运',
      reg_mail_no: '^[A-Za-z0-9-]{4,35}$'
    },
    {
      code: 'STO',
      id: '100',
      name: '申通快递',
      reg_mail_no:
        '^(888|588|688|468|568|668|768|868|968)[0-9]{9}$|^(11|22)[0-9]{10}$|^(STO)[0-9]{10}$|^(37|33|11|22|44|55|66|77|88|99)[0-9]{11}$|^(4)[0-9]{11}$'
    },
    {
      code: 'EMS',
      id: '2',
      name: 'EMS',
      reg_mail_no:
        '^[A-Z]{2}[0-9]{9}[A-Z]{2}$|^(10|11)[0-9]{11}$|^(50|51)[0-9]{11}$|^(95|97)[0-9]{11}$'
    },
    {
      code: 'YUNDA',
      id: '102',
      name: '韵达快递',
      reg_mail_no:
        '^(10|11|12|13|14|15|16|17|19|18|50|55|58|80|88|66|31|77|39)[0-9]{11}$|^[0-9]{13}$'
    },
    {
      code: 'ZTO',
      id: '500',
      name: '中通快递',
      reg_mail_no:
        '^((768|765|778|828|618|680|518|528|688|010|880|660|805|988|628|205|717|718|728|761|762|763|701|757|719|751|358|100|200|118|128|689|738|359|779|852)[0-9]{9})$|^((5711|2008|7380|1180|2009|2013|2010|1000|1010)[0-9]{8})$|^((8010|8021|8831|8013)[0-9]{6})$|^((1111|90|36|11|50|53|37|39|91|93|94|95|96|98)[0-9]{10})$|^((a|b|h)[0-9]{13})$|^((90|80|60)[0-9]{7})$|^((80|81)[0-9]{6})$|^((21|23|24|25|93|94|95|96|97|110|111|112|113|114|115|116|117|118|119|121|122|123|124|125|126|127|128|129|130|131)[0-9]{8})$|^(100|101|102|103|104|105|106|107|503|504|505|506|507)[0-9]{10}$|^(4)[0-9]{11}$|^(120)[0-9]{9}$|^(780)[0-9]{9}$|^(881)[0-9]{9}$|^(882|885)[0-9]{9}$|^(91|92)[0-9]{10}$|^(54|55|56)[0-9]{10}$|^(63)[0-9]{10}$|^(7)[0-9]{9}$|^(64)[0-9]{10}$|^(72)[0-9]{10}$|^(220|221|223|224|225|226|227|228|229)[0-9]{7}$|^(21|22|23|24|25|26|27|28|29)[0-9]{10}$|^3[0-9]{9}$|^2710[0-9]{11}$|^731[0-9]{11}$|^751[0-9]{11}$|^7320[0-9]{10}$'
    },
    {
      code: 'HTKY',
      id: '502',
      name: '百世快递',
      reg_mail_no:
        '^((A|B|D|E)[0-9]{12})$|^(BXA[0-9]{10})$|^(K8[0-9]{11})$|^(02[0-9]{11})$|^(000[0-9]{10})$|^(C0000[0-9]{8})$|^((21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|61|63)[0-9]{10})$|^((50|51)[0-9]{12})$|^7[0-9]{13}$|^6[0-9]{13}$|^58[0-9]{14}$'
    },
    {
      code: 'YTO',
      id: '101',
      name: '圆通速递',
      reg_mail_no:
        '^[A-Za-z0-9]{2}[0-9]{10}$|^[A-Za-z0-9]{2}[0-9]{8}$|^[6-9][0-9]{17}$|^[DD]{2}[8-9][0-9]{15}$|^[Y][0-9]{12}$'
    },
    {
      code: 'QFKD',
      id: '1216',
      name: '全峰快递',
      reg_mail_no:
        '^[0-6|9][0-9]{11}$|^[7][0-8][0-9]{10}$|^[0-9]{15}$|^[S][0-9]{9,11}(-|)P[0-9]{1,2}$|^[0-9]{13}$|^[8][0,2-9][0,2-9][0-9]{9}$|^[8][1][0,2-9][0-9]{9}$|^[8][0,2-9][0-9]{10}$|^[8][1][1][0][8][9][0-9]{6}$'
    },
    {
      code: 'TTKDEX',
      id: '504',
      name: '天天快递',
      reg_mail_no: '^[0-9]{12}$'
    },
    {
      code: 'EYB',
      id: '3',
      name: 'EMS经济快递',
      reg_mail_no:
        '^[A-Z]{2}[0-9]{9}[A-Z]{2}$|^(10|11)[0-9]{11}$|^(50|51)[0-9]{11}$|^(95|97)[0-9]{11}$'
    },
    {
      code: 'UC',
      id: '1207',
      name: '优速快递',
      reg_mail_no:
        '^VIP[0-9]{9}|V[0-9]{11}|[0-9]{12}$|^LBX[0-9]{15}-[2-9AZ]{1}-[1-9A-Z]{1}$|^(9001)[0-9]{8}$'
    },
    {
      code: 'DBKD',
      id: '5000000110730',
      name: '德邦快递',
      reg_mail_no: '^[5789]\\d{9}$'
    },
    {
      code: 'GTO',
      id: '200143',
      name: '国通快递',
      reg_mail_no:
        '^(3(([0-6]|[8-9])\\d{8})|((2|4|5|6)\\d{9})|(7(?![0|1|2|3|4|5|7|8|9])\\d{9})|(8(?![2-9])\\d{9})|(2|4)\\d{11})$'
    },
    {
      code: 'SURE',
      id: '201174',
      name: '速尔快递',
      reg_mail_no: '^(SUR)[0-9]{12}$|^[0-9]{12}$'
    },
    {
      code: 'FEDEX',
      id: '106',
      name: '联邦快递',
      reg_mail_no: '^[0-9]{12}$'
    },
    {
      code: 'SHQ',
      id: '108',
      name: '华强物流',
      reg_mail_no: '^[A-Za-z0-9]*[0|2|4|6|8]$'
    },
    {
      code: 'UAPEX',
      id: '1259',
      name: '全一快递',
      reg_mail_no: '^\\d{12}|\\d{11}$'
    },
    {
      code: 'HOAU',
      id: '1191',
      name: '天地华宇',
      reg_mail_no: '^[A-Za-z0-9]{8,9}$'
    },
    {
      code: 'BEST',
      id: '105',
      name: '百世物流',
      reg_mail_no: '^[0-9]{11,12}$'
    },
    {
      code: 'LB',
      id: '1195',
      name: '龙邦速递',
      reg_mail_no:
        '^[0-9]{12}$|^LBX[0-9]{15}-[2-9AZ]{1}-[1-9A-Z]{1}$|^[0-9]{15}$|^[0-9]{15}-[1-9A-Z]{1}-[1-9A-Z]{1}$'
    },
    {
      code: 'XB',
      id: '1186',
      name: '新邦物流',
      reg_mail_no: '^[0-9]{8}$|^[0-9]{10}$'
    },
    {
      code: 'FAST',
      id: '1204',
      name: '快捷快递',
      reg_mail_no:
        '^(?!440)(?!510)(?!520)(?!5231)([0-9]{9,13})$|^(P330[0-9]{8})$|^(D[0-9]{11})$|^(319)[0-9]{11}$|^(56)[0-9]{10}$|^(536)[0-9]{9}$'
    },
    {
      code: 'POSTB',
      id: '200734',
      name: '邮政快递包裹',
      reg_mail_no:
        '^([GA]|[KQ]|[PH]){2}[0-9]{9}([2-5][0-9]|[1][1-9]|[6][0-5])$|^[99]{2}[0-9]{11}$|^[96]{2}[0-9]{11}$|^[98]{2}[0-9]{11}$'
    },
    {
      code: 'NEDA',
      id: '1192',
      name: '能达速递',
      reg_mail_no: '^((88|)[0-9]{10})$|^((1|2|3|5|)[0-9]{9})$|^(90000[0-9]{7})$'
    },
    {
      code: 'BJRFD-001',
      id: '100034107',
      name: '如风达配送',
      reg_mail_no: '^[\\x21-\\x7e]{1,100}$'
    },
    {
      code: 'DBL',
      id: '107',
      name: '德邦物流',
      reg_mail_no: '^[5789]\\d{9}$'
    },
    {
      code: 'YCT',
      id: '1185',
      name: '黑猫宅急便',
      reg_mail_no: '^[0-9]{12}$'
    },
    {
      code: 'LTS',
      id: '1214',
      name: '联昊通',
      reg_mail_no: '^[0-9]{9,12}$'
    },
    {
      code: 'CNEX',
      id: '1056',
      name: '佳吉快递',
      reg_mail_no: '^[7,1,9][0-9]{9}$'
    },
    {
      code: 'HZABC',
      id: '1121',
      name: '飞远(爱彼西)配送',
      reg_mail_no:
        '^[0-9]{10,11}$|^T[0-9]{10}$|^FYPS[0-9]{12}$|^LBX[0-9]{15}-[2-9AZ]{1}-[1-9A-Z]{1}$'
    },
    {
      code: 'XFWL',
      id: '202855',
      name: '信丰物流',
      reg_mail_no: '^130[0-9]{9}|13[7-9]{1}[0-9]{9}|18[8-9]{1}[0-9]{9}$'
    },
    {
      code: 'ESB',
      id: '200740',
      name: 'E速宝',
      reg_mail_no: '[0-9a-zA-Z-]{5,20}'
    },
    {
      code: 'GDEMS',
      id: '1269',
      name: '广东EMS',
      reg_mail_no: '^[a-zA-Z]{2}[0-9]{9}[a-zA-Z]{2}$'
    },
    {
      code: 'BESTQJT',
      id: '105031',
      name: '百世快运'
    },
    {
      code: 'QRT',
      id: '1208',
      name: '增益速递',
      reg_mail_no: '^[0-9]{12,13}$'
    }
  ]
}