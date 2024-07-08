import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.guess_countryflags.R

class GameViewModel : ViewModel() {
    val score = mutableStateOf(0)
    val flagOptions = mutableStateListOf<String>()
    val correctFlags = mutableStateListOf<String>()
    val guesses = mutableStateListOf<String>()
    val attempts = mutableStateOf(0)
    val guessResult = mutableStateOf<Boolean?>(null)
    var currentFlag = mutableStateOf("")

    fun loadNewFlags(countries: Map<String, String>, count: Int = 3) {
        val randomFlags = countries.keys.shuffled().take(count)
        flagOptions.clear()
        flagOptions.addAll(randomFlags)
        correctFlags.clear()
        correctFlags.addAll(randomFlags.map { countries[it]!! })
        guesses.clear()
        guesses.addAll(List(count) { "" })
        attempts.value = 0
        guessResult.value = null
        currentFlag.value = flagOptions.random()
    }

    fun getFlagResourceId(countryCode: String): Int {
        return when (countryCode) {

            "AD" -> R.drawable.ad
            "AE" -> R.drawable.ae
            "AF" -> R.drawable.af
            "AG" -> R.drawable.ag
            "AI" -> R.drawable.ai
            "AL" -> R.drawable.al
            "AM" -> R.drawable.am
            "AO" -> R.drawable.ao
            "AQ" -> R.drawable.aq
            "AR" -> R.drawable.ar
            "AS" -> R.drawable.`as`
            "AT" -> R.drawable.at
            "AU" -> R.drawable.au
            "AW" -> R.drawable.aw
            "AX" -> R.drawable.ax
            "AZ" -> R.drawable.az
            "BA" -> R.drawable.ba
            "BB" -> R.drawable.bb
            "BD" -> R.drawable.bd
            "BE" -> R.drawable.be
            "BF" -> R.drawable.bf
            "BG" -> R.drawable.bg
            "BH" -> R.drawable.bh
            "BI" -> R.drawable.bi
            "BJ" -> R.drawable.bj
            "BL" -> R.drawable.bl
            "BM" -> R.drawable.bm
            "BN" -> R.drawable.bn
            "BO" -> R.drawable.bo
            "BQ" -> R.drawable.bq
            "BR" -> R.drawable.br
            "BS" -> R.drawable.bs
            "BT" -> R.drawable.bt
            "BV" -> R.drawable.bv
            "BW" -> R.drawable.bw
            "BY" -> R.drawable.by
            "BZ" -> R.drawable.bz
            "CA" -> R.drawable.ca
            "CC" -> R.drawable.cc
            "CD" -> R.drawable.cd
            "CF" -> R.drawable.cf
            "CG" -> R.drawable.cg
            "CH" -> R.drawable.ch
            "CI" -> R.drawable.ci
            "CK" -> R.drawable.ck
            "CL" -> R.drawable.cl
            "CM" -> R.drawable.cm
            "CN" -> R.drawable.cn
            "CO" -> R.drawable.co
            "CR" -> R.drawable.cr
            "CU" -> R.drawable.cu
            "CV" -> R.drawable.cv
            "CW" -> R.drawable.cw
            "CX" -> R.drawable.cx
            "CY" -> R.drawable.cy
            "CZ" -> R.drawable.cz
            "DE" -> R.drawable.de
            "DJ" -> R.drawable.dj
            "DK" -> R.drawable.dk
            "DM" -> R.drawable.dm
            "DZ" -> R.drawable.dz
            "EC" -> R.drawable.ec
            "EE" -> R.drawable.ee
            "EG" -> R.drawable.eg
            "EH" -> R.drawable.eh
            "ER" -> R.drawable.er
            "ES" -> R.drawable.es
            "ET" -> R.drawable.et
            "EU" -> R.drawable.eu
            "FI" -> R.drawable.fi
            "FJ" -> R.drawable.fj
            "FK" -> R.drawable.fk
            "FM" -> R.drawable.fm
            "FO" -> R.drawable.fo
            "FR" -> R.drawable.fr
            "GA" -> R.drawable.ga
            "GB-ENG" -> R.drawable.gb_eng
            "GB-NIR" -> R.drawable.gb_nir
            "GB-SCT" -> R.drawable.gb_sct
            "GB-WLS" -> R.drawable.gb_wls
            "GB" -> R.drawable.gb
            "GD" -> R.drawable.gd
            "GE" -> R.drawable.ge
            "GF" -> R.drawable.gf
            "GG" -> R.drawable.gg
            "GH" -> R.drawable.gh
            "GI" -> R.drawable.gi
            "GL" -> R.drawable.gl
            "GM" -> R.drawable.gm
            "GN" -> R.drawable.gn
            "GP" -> R.drawable.gp
            "GQ" -> R.drawable.gq
            "GR" -> R.drawable.gr
            "GS" -> R.drawable.gs
            "GT" -> R.drawable.gt
            "GU" -> R.drawable.gu
            "GW" -> R.drawable.gw
            "GY" -> R.drawable.gy
            "HK" -> R.drawable.hk
            "HM" -> R.drawable.hm
            "HN" -> R.drawable.hn
            "HR" -> R.drawable.hr
            "HT" -> R.drawable.ht
            "HU" -> R.drawable.hu
            "ID" -> R.drawable.id
            "IE" -> R.drawable.ie
            "IL" -> R.drawable.il
            "IM" -> R.drawable.im
            "IN" -> R.drawable.`in`
            "IO" -> R.drawable.io
            "IQ" -> R.drawable.iq
            "IR" -> R.drawable.ir
            "IS" -> R.drawable.`is`
            "IT" -> R.drawable.it
            "JE" -> R.drawable.je
            "JM" -> R.drawable.jm
            "JO" -> R.drawable.jo
            "JP" -> R.drawable.jp
            "KE" -> R.drawable.ke
            "KG" -> R.drawable.kg
            "KH" -> R.drawable.kh
            "KI" -> R.drawable.ki
            "KM" -> R.drawable.km
            "KN" -> R.drawable.kn
            "KP" -> R.drawable.kp
            "KR" -> R.drawable.kr
            "KW" -> R.drawable.kw
            "KY" -> R.drawable.ky
            "KZ" -> R.drawable.kz
            "LA" -> R.drawable.la
            "LB" -> R.drawable.lb
            "LC" -> R.drawable.lc
            "LI" -> R.drawable.li
            "LK" -> R.drawable.lk
            "LR" -> R.drawable.lr
            "LS" -> R.drawable.ls
            "LT" -> R.drawable.lt
            "LU" -> R.drawable.lu
            "LV" -> R.drawable.lv
            "LY" -> R.drawable.ly
            "MA" -> R.drawable.ma
            "MC" -> R.drawable.mc
            "MD" -> R.drawable.md
            "ME" -> R.drawable.me
            "MF" -> R.drawable.mf
            "MG" -> R.drawable.mg
            "MH" -> R.drawable.mh
            "MK" -> R.drawable.mk
            "ML" -> R.drawable.ml
            "MM" -> R.drawable.mm
            "MN" -> R.drawable.mn
            "MO" -> R.drawable.mo
            "MP" -> R.drawable.mp
            "MQ" -> R.drawable.mq
            "MR" -> R.drawable.mr
            "MS" -> R.drawable.ms
            "MT" -> R.drawable.mt
            "MU" -> R.drawable.mu
            "MV" -> R.drawable.mv
            "MW" -> R.drawable.mw
            "MX" -> R.drawable.mx
            "MY" -> R.drawable.my
            "MZ" -> R.drawable.mz
            "NA" -> R.drawable.na
            "NC" -> R.drawable.nc
            "NE" -> R.drawable.ne
            "NF" -> R.drawable.nf
            "NG" -> R.drawable.ng
            "NI" -> R.drawable.ni
            "NL" -> R.drawable.nl
            "NO" -> R.drawable.no
            "NP" -> R.drawable.np
            "NR" -> R.drawable.nr
            "NU" -> R.drawable.nu
            "NZ" -> R.drawable.nz
            "OM" -> R.drawable.om
            "PA" -> R.drawable.pa
            "PE" -> R.drawable.pe
            "PF" -> R.drawable.pf
            "PG" -> R.drawable.pg
            "PH" -> R.drawable.ph
            "PK" -> R.drawable.pk
            "PL" -> R.drawable.pl
            "PM" -> R.drawable.pm
            "PN" -> R.drawable.pn
            "PR" -> R.drawable.pr
            "PS" -> R.drawable.ps
            "PT" -> R.drawable.pt
            "PW" -> R.drawable.pw
            "PY" -> R.drawable.py
            "QA" -> R.drawable.qa
            "RE" -> R.drawable.re
            "RO" -> R.drawable.ro
            "RS" -> R.drawable.rs
            "RU" -> R.drawable.ru
            "RW" -> R.drawable.rw
            "SA" -> R.drawable.sa
            "SB" -> R.drawable.sb
            "SC" -> R.drawable.sc
            "SD" -> R.drawable.sd
            "SE" -> R.drawable.se
            "SG" -> R.drawable.sg
            "SH" -> R.drawable.sh
            "SI" -> R.drawable.si
            "SJ" -> R.drawable.sj
            "SK" -> R.drawable.sk
            "SL" -> R.drawable.sl
            "SM" -> R.drawable.sm
            "SN" -> R.drawable.sn
            "SO" -> R.drawable.so
            "SR" -> R.drawable.sr
            "SS" -> R.drawable.ss
            "ST" -> R.drawable.st
            "SV" -> R.drawable.sv
            "SX" -> R.drawable.sx
            "SY" -> R.drawable.sy
            "SZ" -> R.drawable.sz
            "TC" -> R.drawable.tc
            "TD" -> R.drawable.td
            "TF" -> R.drawable.tf
            "TG" -> R.drawable.tg
            "TH" -> R.drawable.th
            "TJ" -> R.drawable.tj
            "TK" -> R.drawable.tk
            "TL" -> R.drawable.tl
            "TM" -> R.drawable.tm
            "TN" -> R.drawable.tn
            "TO" -> R.drawable.to
            "TR" -> R.drawable.tr
            "TT" -> R.drawable.tt
            "TV" -> R.drawable.tv
            "TW" -> R.drawable.tw
            "TZ" -> R.drawable.tz
            "UA" -> R.drawable.ua
            "UG" -> R.drawable.ug
            "UM" -> R.drawable.um
            "US" -> R.drawable.us
            "UY" -> R.drawable.uy
            "UZ" -> R.drawable.uz
            "VA" -> R.drawable.va
            "VC" -> R.drawable.vc
            "VE" -> R.drawable.ve
            "VG" -> R.drawable.vg
            "VI" -> R.drawable.vi
            "VN" -> R.drawable.vn
            "VU" -> R.drawable.vu
            "WF" -> R.drawable.wf
            "WS" -> R.drawable.ws
            "XK" -> R.drawable.xk
            "YE" -> R.drawable.ye
            "YT" -> R.drawable.yt
            "ZA" -> R.drawable.za
            "ZM" -> R.drawable.zm
            "ZW" -> R.drawable.zw
            // Add other country codes and their corresponding drawable resources
            else -> R.drawable.us // Default placeholder
        }
    }
}
