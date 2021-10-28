package com.bms.roleodado

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class DiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)

        rollInit()

        button.setOnClickListener{
            button.setBackgroundColor(Color.RED)
            button.setText(R.string.calcTeste)
            rollDice()
        }
    }

    private fun rollInit() {
        // Find the ImageView in the layout
        val diceImage: ImageView = findViewById(R.id.dice)
        diceImage.setImageResource(R.drawable.dice_2)

        Toast.makeText(this,"Hey... Vamos jogar!!!", Toast.LENGTH_SHORT).show()
    }

    private fun rollDice() {
        // Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()

        // Find the ImageView in the layout
        val diceImage: ImageView = findViewById(R.id.dice)

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
                    //versao anterior
                    // 6 -> diceImage.setImageResource(R.drawable.dice_6)
            6-> R.drawable.dice_6
            else -> R.drawable.ic_launcher_foreground
            //correção do erro when
        }

        // Update the ImageView with the correct drawable resource ID
        diceImage.setImageResource(drawableResource)

        // Update the content description
        diceImage.contentDescription = diceRoll.toString()

        //show a toast result
        val show = diceRoll.toString()
        Toast.makeText(this,"Você tirou = $show", Toast.LENGTH_SHORT).show()
    }

    class Dice (val numSides: Int){
        fun roll():Int{
            return (1..numSides).random()
            //retorna um número aleatório de 1 ate o numero de lados - neste caso 6
        }
    }

    //inflando layout do menu :
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    //quando um dos itens for selecionado no menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.item_menu -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.item_menu2 -> {
                true
            }
            R.id.item_menu3 -> {
                val intent2 =Intent(this, LemonActivity::class.java)
                startActivity(intent2)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}