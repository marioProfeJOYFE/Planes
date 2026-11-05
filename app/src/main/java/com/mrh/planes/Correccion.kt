package com.mrh.planes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*


Crea una data class Plan con
id: Int, nombre: String, precio: Int,
 categoria: String, abierto: Boolean,
 favorito: Boolean y nota: String?.
 Usa propiedades val
 */

data class Plan(
    val id: Int,
    val nombre: String,
    val precio: Int,
    val categoria: String,
    val abierto: Boolean,
    val favorito: Boolean,
    val nota: String?
)



@Composable
fun FichaPlan(plan: Plan, modifier: Modifier = Modifier){
    Column(
        modifier = modifier.padding(16.dp)
    ){
        Box(
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(R.drawable.plan_logo) ,
                contentDescription = "Imagen de Puntero",
                modifier = Modifier.size(90.dp)
            )
            Text(text = "PLAN")
        }
        Text(text = plan.nombre, fontSize = 22.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "${plan.precio} €")
            Text(text = if(plan.abierto) "Abierto" else "Cerrado")
        }
        Text(text = plan.categoria)
        Text(text = plan.nota ?: "Sin observaciones")
        if(plan.favorito){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Icon(
                    painter = painterResource(R.drawable.plan_logo),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text(text = "Favorito")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {

            }
        ) {
            Text(text = "Elegir Plan")
        }
    }
}


@Preview
@Composable
fun FichaPlanPreview(){
    FichaPlan(
        plan = Plan(
            id = 0,
            nombre = "Champions",
            precio = 20,
            categoria = "Futbol",
            abierto = false,
            favorito = true,
            nota = null
        )
    )
}
