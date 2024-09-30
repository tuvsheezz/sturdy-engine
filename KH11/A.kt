import java.io.*
import java.lang.Math.abs
import java.lang.Math
import java.util.*
import java.math.BigInteger
import kotlin.system.exitProcess
import kotlin.math.sqrt

class Kattio: PrintWriter {
    @JvmField val r: BufferedReader
    @JvmField var st = StringTokenizer("")
    constructor(): this(System.`in`,System.out) {}
    constructor(i: InputStream, o: OutputStream): super(o,false) { r = i.bufferedReader() }
    fun rLine(): String? = r.readLine()

    fun read(): String {
        while (st.hasMoreTokens().not()) st =
            StringTokenizer(rLine() ?: return "", " ")
        return st.nextToken()
    }
    fun strs(n: Int) = List(n){read()}
    fun ln() = rLine()!!
    fun lns(n: Int) = List(n){ln()}
    fun int() = read().toInt()
    fun ints(n: Int) = List(n){int()}
    fun db() = read().toDouble()
    fun rDbs(n: Int) = List(n){db()}
    fun long() = read().toLong()
    fun longs(n: Int) = List(n){long()}
}

val io = Kattio()


fun solveA() {
    val n = io.int()
    var x = io.ints(n).sorted()
    if(x[n - 1] - x[n - 2] > 1) {
        println("Ambiguous")
    } else {
        println(x[n - 2])
    }
}

fun solveB() {
    val n = io.int()
    print("1")
    for(i in n downTo 2) {
        print(" ${i}")
    }
    println()
}

fun solveC() {
    var s = io.strs(1)[0].split("+")
    var ans: Long = 0;
    val n = s.size
    for(i in 0 until n) {
        if(i == 0 || i == n - 1) ans += s[i].toLong()
        else {
            val tmpl = s[i].length
            var mx = maxOf(s[i].substring(0, 1).toLong() + s[i].substring(1, tmpl).toLong(),
            s[i].substring(0, tmpl - 1).toLong() + s[i].substring(tmpl - 1, tmpl).toLong())
            ans += mx
        }
    }
    println(ans)
}

fun solveD() {
    val (n, kill, build) = io.longs(3)
    val s = io.strs(2)
    var ans = 3 * build
    var sheep: Long = -1
    var left: Long = 0
    var right: Long = 0
    for(i in 0 until n) {
        for(j in 0 until 2) {
            if(s[j][i.toInt()] == 'S') sheep = i
            if(s[j][i.toInt()] == 'W') {
                if(sheep == -1.toLong()) left++
                else right++
            }
        }
    }
    // kill all wolves
    ans = minOf(ans, left * kill + right * kill)
    // kill all wolves to the left
    ans = minOf(ans, left * kill + 2 * build)
    // kill all wolves to the right
    ans = minOf(ans, right * kill + 2 * build)
    println(ans)
}

fun eHelper(S: String, prefix: String): Int {
    var s = prefix + S
    var x = s.split("").filter { it != "" }
    var hands = mutableListOf<Char>();
    var n = x.size

    for(i in 1 until n) {
        if(x[i] == "R") {
            hands.add('S')
        } else if(x[i] == "S") {
            hands.add('P')
        } else {
            hands.add('R')
        }
    }

    var sc = 1
    var sc2 = 1
    val st = mutableMapOf<String, Int>(
        "RS" to -1, "RR" to 0, "RP" to 1,
        "SP" to -1, "SS" to 0, "SR" to 1,
        "PR" to -1, "PP" to 0, "PS" to 1
    )

    for(i in 0 until n - 1) {
        sc += st[x[i] + hands[i]]!!
        if(i != 0) sc2 += st[x[i] + hands[i]]!!
    }
    var ans: Int

    if(sc > 0) ans = n
    else {
        ans = n - sc + 1
    }
    return ans
}

fun solveE() {
    var s = io.strs(1)[0]
    var ans = 1e9.toInt()
    if(!s.startsWith("R")) {
        ans = minOf(ans, eHelper(s, "R"))
    } else {
        ans = minOf(ans, eHelper(s, ""))
    }
    ans = minOf(ans, eHelper(s, "R"))
    ans = minOf(ans, eHelper(s, "RS"))
    ans = minOf(ans, eHelper(s, "RSP"))
    println(ans)
}

fun main() {
    val t = io.int()
    for(i in 0 until t) { solveE() }
    io.close()
}
