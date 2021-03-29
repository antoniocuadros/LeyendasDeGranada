package com.acl.leyendasdelaalhambra

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

class AccesoDatos {
    lateinit var listaLeyendas:MutableList<Leyenda>;
    lateinit var recorridos:MutableList<Recorrido>

    constructor(context: Context?){
        /*
        listaLeyendas = mutableListOf(
                Leyenda(1, "Leyenda del Patio de los Leones",
                        "Hace muchos años una Princesa llamada Zaira, viajó hasta Granada con su padre. Se alojaron en las dependencias de La Alhambra. Zaira se sentía muy sola ya que solo contaba con la compañía de su padre y un séquito de 11 hombre que velaban por su seguridad. Un día, la Princesa leyó el diario de su padre donde contaba que había matado a los verdaderos padres de Zaira cuando ella tenía 1 año.Al parecer, su madre había echado un maleficio al talismán y si la niña algún día se enteraba de la verdad, una maldición caería sobre el Rey.\n"
                                +"\n"+
                                "En el patio donde ahora se encuentra el Patio de Los Leones, la joven le preguntó a su padre si lo que había leído era cierto, su padre lo afirmó y cuenta la leyenda que el talismán se activó y su padre con los 11 hombres se convirtieron en 12 leones de piedra, los que coronan hoy en día el Patio de los Leones.",
                        "https://i.imgur.com/ABQw1si.jpg",
                        37.17710111654708,
                        -3.589237144374555,
                        "POPULARES ALHAMBRA",
                        "Patio de los Leones",
                        6,
                        "https://www.civitatis.com/blog/leyendas-de-la-alhambra-de-granada/"
                ),
                Leyenda(2, "Leyenda de la Puerta de la Justicia",
                        "Cuenta la leyenda que tan sumamente recia era la construcción de la Alhambra que, aún recibiendo el ataque de mil ejércitos enemigos, jamás caería.\n"
                                +"\n"+
                                "Se decía que el día en que la llave del arco interior de la Puerta de la Justicia y la mano de su arco exterior se unan, la Alhambra caerá porque ese día habrá llegado el fin del mundo. También asegura la leyenda que tal era la magnificiencia del Arco de la Justicia, que se aseguraba que ningún caballero, montado a caballo con su lanza, podría tocar con la punta de ésta la mano esculpida en lo alto del arco exterior. Tan seguros estaban de ello, que aseguraban que quien lo consiguiese conquistaría el trono de la Alhambra.",
                        "https://i.imgur.com/QOsNgGs.jpg",
                        37.17615558110561, -3.5902839954200045,
                        "POPULARES ALHAMBRA",
                        "Puerta de la Justicia",
                        1,
                    "https://www.alhambra.info/leyendas-alhambra.html"
                ),
                Leyenda(3, "Leyenda de la sala  de los Abencerrajes",
                        "El nombre de Abencerrajes proviene del apellido de una familia de la nobleza de la época, que tenían sus viviendas en el interior de la Alhambra.\n"
                                +"\n"+
                                "Cuenta la leyenda que esta familia tenía como rival político a otra llamada Zenetes, los cuales decidieron acabar con sus oponentes mediante una conspiración... Así, inventaron una relación amorosa entre la sultana y uno de los Abencerrajes, para conseguir despertar los celos y la ira en el sultán... El sultán, cegado por la consternación, y en ocasión de una fiesta en la sala que lleva el nombre de la familia, hizo decapitar sobre su fuente a los 37 caballeros que llevaban el nombre de Abencerrajes.\n"
                                +"\n"+
                                "Se cuenta que el color rojizo que aun hoy día se puede contemplar en la taza de la fuente, y en el canal que lleva su agua hasta la fuente del Patio de los Leones, se debe a las manchas de la sangre de los caballeros asesinados...",
                        "https://i.imgur.com/X31ERYJ.jpg",
                        37.17695623969098, -3.5892068886337682,
                        "WASHINGTON IRVING",
                        "Sala de los Abencerrajes",
                        2,
                    "https://www.alhambra.info/leyendas-alhambra.html"
                ),
                Leyenda(4, "Leyenda de la silla del moro",
                        "Más allá del Generalife (cuando se observa desde la Alhambra), puede observarse una desnuda y pelada colina que está coronada por unas ruinas. Aún hoy día esta colina es conocida como La Silla del Moro.\n"
                                +"\n"+
                                "Esto se debe a que, según cuenta la leyenda, debido a una insurrección en la Ciudad de la Alhambra, el rey Boabdil (último gobernante de la Granada musulmana) tuvo que buscar refugio en este monte. Fue desde allí donde se sentó tristemente a contemplar su amotinada Alhambra...",
                        "https://i.imgur.com/lBd13f2.jpg",
                        37.17835794606415, -3.583555607799876,
                        "WASHINGTON IRVING",
                        "Silla del Moro",
                        5,
                    "https://www.alhambra.info/leyendas-alhambra.html"
                ),
                Leyenda(5, "Leyenda de la torre de la cautiva",
                        "Cuenta la leyenda que Isabel de Solis, fue una cristiana capturada por Mulay Hazen y de la que posteriormente se enamoró. Un romance imposible si ella no se hubiese convertido al Islam, como finalmente ocurrió, convirtiéndose así en la reina Zoraida de Granada.\n"
                                +"\n" +
                                "Sin embargo, la torre en la que estuvo secuestrada al principio se conoce desde entonces como Torre de la Cautiva, desde donde además hay unas espectaculares vistas de los llamados jardines multicolores.",
                        "https://i.imgur.com/7GAqPkg.jpg",
                        37.17618671746534, -3.5862958470318413,
                        "POPULARES ALHAMBRA",
                        "Torre de la Cautiva",
                        6,
                    "https://www.elviajerofisgon.com/magazine/las-7-leyendas-la-alhambra-no-podras-olvidar/"
                ),
                Leyenda(6, "Leyenda del ciprés de la sultana",
                        "Dicen que si pasas tu mano por el desgastado tronco del Ciprés de la Sultana encontrarás un amor apasionado.\n"
                                +"\n"+
                                "Cuenta la leyenda que este ciprés, plantado en un patio del Generalife, fue testigo mudo de los amores furtivos de Morayma, esposa del rey Boabdil, y un apuesto caballero de la tribu de los Abencerrajes. Los encuentros de los amantes a su sombra, en noches de luna llena, fueron delatados al último rey moro de Granada.\n"
                                + "\n" +
                                "La ira de Boabdil fue tal que en represalia mandó degollar a varios caballeros de la noble tribu musulmana.",
                        "https://i.imgur.com/zF3wUEg.jpg",
                        37.17825041122603, -3.5854813411045283,
                        "POPULARES ALHAMBRA",
                        "Patio de la Sultana",
                        7,
                    "https://www.turgranada.es/fichas/cipres-del-patio-de-la-acequia-del-generalife-46922/"
                ),
                Leyenda(7,
                        "Leyenda del soldado encantado",
                        "Cuenta la leyenda que Don Vicente era un estudiante de Salamanca, a quien le gustaba viajar durante el verano por los pueblos, tocando su guitarra y ganando dinero con sus canciones. Una vez llegó a Granada la víspera de San Juan. Se encontró con un soldado perfectamente armado que montaba guardia, y que pasaba desapercibido para todo el mundo. Don Vicente le preguntó quién era. El soldado le contestó que le habían cogido prisionero en un ataque contra los moros 300 años antes. Le habían lanzado un hechizo que le obligaba a vigilar el tesoro de los moros y evitar que cayese en manos cristianas.\n" +
                                "\n" +
                                "Sólo era liberado temporalmente una vez cada 100 años, en la víspera de San Juan, cuando el hechizo permitía que cualquier persona lo viera y pudiera liberarlo. El desesperado soldado prometió a don Vicente la mitad del tesoro a cambio de su ayuda. Su misión consistía en encontrar un sacerdote en ayuno para librarlo del hechizo y una doncella pura para abrir el cofre del tesoro. Don Vicente salió a buscar ayuda y encontró una chica casta y un cura de apetito insaciable. El cura haría lo posible para ignorar su apetito.\n" +
                                "\n" +
                                "Una vez que don Vicente, el cura y la chica llegaron al pie de la torre, la cripta apareció. Una vez dentro, el hechizo parecía roto. Entonces Don Vicente comenzó a llenar sus bolsillos con oro. El cura glotón comió algo de fruta. En un visto y no visto, Don Vicente, la doncella y el cura se encontraron fuera de la torre. La Noche de San Juan había terminado y el hechizo no se había deshecho… El sacerdote había comido demasiado pronto.\n" +
                                "\n" +
                                "Según cuenta la leyenda, todavía el soldado permanece prisionero en la torre, donde se le puede ver vigilando el Tesoro de la Alhambra… ",
                        "https://i.imgur.com/aLKbTg4.jpg",
                        37.17686273868222, -3.5920133287818055,
                        "WASHINGTON IRVING",
                        "Torre de Vigilancia, Alcazaba",
                        1,
                    "https://granadapedia.wikanda.es/wiki/Leyenda_del_Soldado_Encantado"
                ),
                Leyenda(8, "Leyenda del origen del nombre 'Alhambra'",
                        "El nombre con el que se conoce a la Alhambra procede de una palabra musulmana cuyo significado es \"fortaleza roja\".\n"
                                +"\n"+
                                "Sin embargo, existen evidencias históricas de que la Alhambra era de un color blanco resplandeciente. Cuenta la leyenda que la razón más aceptada de por qué se le conocia por castillo rojo está en su apresurada construcción. Eran muchos los obreros que participaban en la construcción, y el color rojo provenía de sus hachas brillando al sol. Además, por la noche se encendían fogatas para iluminar los trabajos de construcción, lo que también le daba un aspecto rojizo para quien la observara desde la Vega de Granada.",
                        "https://i.imgur.com/vYKCRwW.jpg",
                        37.176849988938976, -3.590408805011711,
                        "POPULARES ALHAMBRA",
                        "Alhambra",
                        3,
                    "https://granadapedia.wikanda.es/wiki/La_Leyenda_sobre_el_nombre_%22Alhambra%22"
                ),
                Leyenda(9, "Leyenda de los azulejos de Mexuar",
                        "Es ésta la sala más antigua del palacio. El sultán se situaba en ella, dentro de una cámara elevada, oculta por celosías, con el fin de escuchar sin ser visto." +
                                "\n" +
                                "Desde allí prestaba audiencia e impartía justicia. Es un hecho históricamente comprobado que el sultán tenía en la época cualidad judicial, y sus sentencias eran conocidas por ecuánimes e imparciales.\n"
                                +"\n"+
                                "En su puerta, anunciando su razón de ser, había un azulejo con un cartel que rezaba: \"Entra y pide. No temas pedir justicia, que hallarla has\".",
                        "https://i.imgur.com/BHvCw1U.jpg",
                        37.177248324694595, -3.590057778130835,
                        "POPULARES ALHAMBRA",
                        "Mexuar",
                        4,
                    "https://www.revistaiberica.com/ocho-leyendas-de-la-alhambra/"
                ),
                Leyenda(
                        10, "Leyenda de las tres infantas",
                        "Cuenta la leyenda que hubo un rey que tenía tres hermosas hijas: Zayda, Zorayda y Zorahayda. Advertido por un astrólogo que corrían peligro de hacer un mal casamiento, indigno del rango de princesas, las encerró en una torre.\n"
                                +"\n"+
                                "Sin embargo fue vano intento ya que las jóvenes desde su ventana se enamoraron de tres caballeros cristianos que habían sido capturados por los soldados musulmanes. Las princesas se las ingeniaron para conocer a los prisioneros, y cuando sus familias pagaron el rescate e iban a partir de la Alhambra urdieron un plan de fuga, para marcharse con ellos. Sin embargo, llegado el momento la más joven de las tres, Zorahayda, no se atrevió a huir, quedando sola. Murió joven y triste y sobre su sepultura creció una rosa, conocida como «la rosa de la Alhambra».",
                        "https://i.imgur.com/CCOwB34.jpg",
                        37.17565620470659, -3.5858588693904245,
                        "WASHINGTON IRVING",
                        "Torre de las Infantas",
                        3,
                    "https://www.civitatis.com/blog/leyendas-de-la-alhambra-de-granada/"
                ),

                Leyenda(
                        11, "Leyenda de Ahmed al Kamel",
                        "Cuenta la leyenda como en el Generalife se encerró de niño al príncipe Ahmed, a quien un vidente le había pronosticado un buen futuro excepto en el amor. El rey, para evitar que sufriera, lo recluyó en el Generalife con su filósofo Abben, bajo la pena de que si este le explicaba lo que era el amor le cortaría la cabeza.\n"
                                +"\n"+
                                "El muchacho creció y cada vez le interesaban menos los estudios, por lo que Abben le enseñó la lengua de los pájaros. Un día escuchó a un pájaro entonar el “canto del amor”, lo que le suscitó mucha curiosidad. Al poco tiempo cayó del cielo un ave perseguida por un halcón y Amhed la curó. A cambio, le preguntó qué era el amor. Una vez lo supo no podía quitárselo de la cabeza y escapaba del Generalife a observar a las muchachas de la corte. Finalmente se enamoró de una de ellas y juntos se marcharon, siendo, contrariamente a lo que le vaticinaron, muy felices.",
                        "https://i.imgur.com/wXx5nBA.jpg",
                        37.17680739307767, -3.585132236728954,
                        "WASHINGTON IRVING",
                        "Generalife",
                        4,
                    "https://www.civitatis.com/blog/leyendas-de-la-alhambra-de-granada/"
                ),
                Leyenda(
                        12, "Leyenda de la campana de la Vela",
                        "Según cuenta la tradición, la mujer soltera que toque la campana que se encuentra en la parte superior de la Torre de la Vela, la más alta de la torre, el dos de enero, día de la Toma de Granada, ese año encontrará marido y se casará.",
                        "https://i.imgur.com/2qkHe33.jpg",
                        37.177025204856676, -3.592285705487403,
                        "POPULARES ALHAMBRA",
                        "Torre de la vela",
                        2,
                    "https://www.civitatis.com/blog/leyendas-de-la-alhambra-de-granada/"
                )
        )
        */
        listaLeyendas = obtener_datos_leyendas_json(context)
        recorridos = mutableListOf(
            Recorrido(1,
                "Leyendas Alhambra WASHINGTON IRVING",
                "Conjunto de leyendas de la Alhambra",
                "https://i.imgur.com/q7WAIXz.jpg",
                this.obtenerLeyendasRecorrido("WASHINGTON IRVING")),
            Recorrido(1,
                "Leyendas Alhambra origen desconocido",
                "Conjunto de leyendas de la Alhambra",
                "https://i.imgur.com/dXP2uLL.jpg",
                this.obtenerLeyendasRecorrido("POPULARES ALHAMBRA"))
        )
        
    }

    private fun obtener_datos_leyendas_json(context: Context?): MutableList<Leyenda> {
        //Hay que leer el archivo JSON y parsearlo a un objeto leyenda
        //Extraemos en string el Json
        val leyendas_texto:String
        val inputStream:InputStream = context?.assets!!.open("leyendas.json")

        leyendas_texto = inputStream.bufferedReader().use{it.readText()}

        var listaLeyendas:MutableList<Leyenda>

        val gson = Gson()
        val tipo_a_leer = object :TypeToken<MutableList<Leyenda>>() {}.type

        listaLeyendas = gson.fromJson<MutableList<Leyenda>>(leyendas_texto, tipo_a_leer)

        return listaLeyendas

    }


    public fun obtenerLeyendas():MutableList<Leyenda>{
        return listaLeyendas;
    }

    public fun obtenerRecorridos():MutableList<Recorrido>{
        return recorridos;
    }

    public fun obtenerLeyendasRecorrido(recorrido:String):MutableList<Leyenda>{
        var a_devolver = mutableListOf<Leyenda>();

        for(leyenda in listaLeyendas){
            if(leyenda.recorrido == recorrido){
                a_devolver.add(leyenda);
            }
        }
        return a_devolver;
    }
}