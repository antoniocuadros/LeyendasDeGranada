package com.acl.leyendasdelaalhambra

class AccesoDatos {
    lateinit var listaLeyendas:MutableList<Leyenda>;

    constructor(){
        listaLeyendas = mutableListOf(
                Leyenda(1, "Leyenda del Patio de los Leones",
                        "Hace muchos años una Princesa llamada Zaira, viajó hasta Granada con su padre. Se alojaron en las dependencias de La Alhambra. Zaira se sentía muy sola ya que solo contaba con la compañía de su padre y un séquito de 11 hombre que velaban por su seguridad. Un día, la Princesa leyó el diario de su padre donde contaba que había matado a los verdaderos padres de Zaira cuando ella tenía 1 año.Al parecer, su madre había echado un maleficio al talismán y si la niña algún día se enteraba de la verdad, una maldición caería sobre el Rey. En el patio donde ahora se encuentra el Patio de Los Leones, la joven le preguntó a su padre si lo que había leído era cierto, su padre lo afirmó y cuenta la leyenda que el talismán se activó y su padre con los 11 hombres se convirtieron en 12 leones de piedra, los que coronan hoy en día el Patio de los Leones.",
                        "https://i.imgur.com/ABQw1si.jpg",
                        37.17710111654708,
                        -3.589237144374555,
                        "Recorrido 1"),
                Leyenda(1, "Leyenda de la Puerta de la Justicia",
                        "Cuenta una leyenda sobre la puerta de la Justicia, relacionada con la construcción misma de la Alhambra. Siempre se ha hablado de la dedicación puesta en la construcción de la Alhambra, tanto en lo decorativo como en lo arquitectónico. Se asegura que tan sumamente recia era su construcción que, aún recibiendo el ataque de mil ejércitos enemigos, jamás caería. Así pues, el día que la llave del arco interior de la Puerta de la Justicia y la mano de su arco exterior se unan, es decir, si la Alhambra cae, será por que ha llegado el fin del mundo.",
                        "https://i.imgur.com/QOsNgGs.jpg",
                        1.1231,
                        2.21313,
                        "Recorrido 1"),
                Leyenda(1, "Leyenda de la sala de los Abencerrajes",
                        "El nombre de Abencerrajes proviene del apellido de una familia de la nobleza de la época, que tenían sus viviendas en el interior de la Alhambra. Dice la leyenda que esta familia tenía como rival político a otra llamada Zenetes, los cuales decidieron acabar con sus oponentes mediante una conspiración... Así, inventaron una relación amorosa entre la sultana y uno de los Abencerrajes, para conseguir despertar los celos y la ira en el sultán... El sultán, cegado por la consternación, y en ocasión de una fiesta en la sala que lleva el nombre de la familia, hizo decapitar sobre su fuente a los 37 caballeros que llevaban el nombre de Abencerrajes. Se cuenta que el color rojizo que aun hoy día se puede contemplar en la taza de la fuente, y en el canal que lleva su agua hasta la fuente del Patio de los Leones, se debe a las manchas de la sangre de los caballeros asesinados...",
                        "https://i.imgur.com/X31ERYJ.jpg",
                        37.17695623969098, -3.5892068886337682,
                        "Recorrido 1"),
                Leyenda(1, "Leyenda de la silla del moro",
                        "Más allá del Generalife (cuando se observa desde la Alhambra), puede observarse una desnuda y pelada colina que está coronada por unas ruinas. Aún hoy día esta colina es conocida como La Silla del Moro. Esto se debe a que, debido a una insurrección en la Ciudad de la Alhambra, el rey Boabdil (último gobernante de la Granada musulmana) tuvo que buscar refugio en este monte. Fue desde allí donde se sentó tristemente a contemplar su amotinada Alhambra...",
                        "https://i.imgur.com/lBd13f2.jpg",
                        37.17835794606415, -3.583555607799876,
                        "Recorrido 1"),
                Leyenda(1, "Leyenda del Patio de los Leones5", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1"),
                Leyenda(1, "Leyenda del Patio de los Leones6", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1"),
                Leyenda(1, "Leyenda del Patio de los Leones7", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1"),
                Leyenda(1, "Leyenda del Patio de los Leones8", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1"),
                Leyenda(1, "Leyenda del Patio de los Leones9", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1")

        )
    }

    public fun obtenerLeyendas():MutableList<Leyenda>{
        return listaLeyendas;
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