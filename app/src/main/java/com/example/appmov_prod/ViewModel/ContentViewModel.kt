package com.example.appmov_prod.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmov_prod.Model.Content

class ContentViewModel : ViewModel() {

    private val _featuredNews = MutableLiveData<List<Content>>()
    val featuredNews: LiveData<List<Content>> = _featuredNews

    private val _articles = MutableLiveData<List<Content>>()
    val articles: LiveData<List<Content>> = _articles

    private val _news = MutableLiveData<List<Content>>()
    val news: LiveData<List<Content>> = _news

    init {
        _featuredNews.value = listOf(
            Content(
                title = "Primera estación de reciclaje en Nuevo Chimbote",
                imageUrl = "https://diariocorreo.pe/resizer/R37k16aZE43d2i3z6XaKdkXvTfM=/1200x799/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/CVNUARP4GZGXNLJ7PXGRHYICKY.jpg",
                contentUrl = "https://diariocorreo.pe/edicion/chimbote/primera-estacion-de-reciclaje-en-nuevo-chimbote-peru-noticia/"
            ),
            Content(
                title = "Día Mundial del Medio Ambiente",
                imageUrl = "https://portal.andina.pe/EDPfotografia3/Thumbnail/2018/06/05/000508593W.jpg",
                contentUrl = "https://andina.pe/agencia/noticia-dia-mundial-del-medio-ambiente-daran-nuevo-uso-a-desechos-solidos-chimbote-712333.aspx"
            )
        )
        _articles.value = listOf(
            Content(
                title = "Primera estación de reciclaje en Nuevo Chimbote",
                imageUrl = "https://diariocorreo.pe/resizer/R37k16aZE43d2i3z6XaKdkXvTfM=/1200x799/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/CVNUARP4GZGXNLJ7PXGRHYICKY.jpg",
                contentUrl = "https://diariocorreo.pe/edicion/chimbote/primera-estacion-de-reciclaje-en-nuevo-chimbote-peru-noticia/"
            ),
            Content(
                title = "Municipio reactiva programa 'Nuevo Chimbote recicla'",
                imageUrl = "https://www.chimbotenlinea.com/sites/default/files/styles/grande/public/field/image/whatsapp_image_2020-09-25_at_02.07.44.jpeg?itok=Owk1bZyP",
                contentUrl = "https://www.chimbotenlinea.com/nuevo-chimbote/24/09/2020/municipio-reactiva-programa-nuevo-chimbote-recicla"
            )
        )
        _news.value = listOf(
            Content(
                title = "Nuevo Chimbote: recolectan 400 kilos de residuos aprovechables a través del Reciclamóvil",
                imageUrl = "https://rsdradio.s3.sa-east-1.amazonaws.com/2024-02/reciclaje.jpg",
                contentUrl = "https://radiorsd.pe/noticias/nuevo-chimbote-recolectan-400-kilos-de-residuos-aprovechables-traves-del-reciclamovil"
            ),
            Content(
                title = "Chimbote: inician campaña de reciclaje de plásticos para reducir la contaminación",
                imageUrl = "https://rsdradio.s3.sa-east-1.amazonaws.com/2023-08/chimbote%20recicla.jpeg",
                contentUrl = "https://radiorsd.pe/index.php/noticias/chimbote-inician-campana-de-reciclaje-de-plasticos-para-reducir-la-contaminacion"
            ),
            Content(
                title = "Nuevo Chimbote posee moderno relleno sanitario y planta de valorización de residuos",
                imageUrl = "Nuevo Chimbote posee moderno relleno sanitario y planta de valorización de residuos",
                contentUrl = "https://andina.pe/agencia/noticia-nuevo-chimbote-posee-moderno-relleno-sanitario-y-planta-valorizacion-residuos-927488.aspx"
            )
        )
    }
}
