package com.bms.roleodado

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            button.setBackgroundColor(Color.BLACK)
            button.setText(R.string.calcTeste)
            Toast.makeText(this, "Rolando...", Toast.LENGTH_SHORT).show()
            rollDice()
            //val resultText = text
            //resultText.text= "6"
        }


    }

    private fun rollDice() {
        // Create new Dice object with 6 sides and roll it
        val dice = MyDice(6)
        val diceRoll = dice.roll()

        val dice2 = MyDice(6)
        val diceRoll2 = dice2.roll()

        // Update the screen with the dice roll
        val resultRoll = text
        resultRoll.text = diceRoll.toString()

        val resultRoll2 = text2
        resultRoll2.text = diceRoll2.toString()

        val total = text3
        total.text= (diceRoll + diceRoll2).toString()


    }

    class MyDice (val numSides: Int){
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
                true
            }
            R.id.item_menu2 -> {
                val intent2 =Intent(this, DiceActivity::class.java)
                startActivity(intent2)
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