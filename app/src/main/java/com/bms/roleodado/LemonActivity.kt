package com.bms.roleodado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_lemon.*

class LemonActivity : AppCompatActivity() {


    /**
     * DO NOT ALTER ANY VARIABLE OR VALUE NAMES OR THEIR INITIAL VALUES.
     *
     * Anything labeled var instead of val is expected to be changed in the functions but DO NOT
     * alter their initial values declared here, this could cause the app to not function properly.
     */
    private val LEMONADE_STATE = "LEMONADE_STATE"
    private val LEMON_SIZE = "LEMON_SIZE"
    private val SQUEEZE_COUNT = "SQUEEZE_COUNT"

    // SELECT represents the "pick lemon" state
    private val SELECT = "select"

    // SQUEEZE represents the "squeeze lemon" state
    private val SQUEEZE = "squeeze"

    // DRINK represents the "drink lemonade" state
    private val DRINK = "drink"

    // RESTART represents the state where the lemonade has be drunk and the glass is empty
    private val RESTART = "restart"

    // Default the state to select
    private var lemonadeState = "select"

    // Default lemonSize to -1
    private var lemonSize = -1

    // Default the squeezeCount to -1
    private var squeezeCount = -1

    private var lemonTree = LemonTree()
    private var lemonImage: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lemon)
        // === DO NOT ALTER THE CODE IN THE FOLLOWING IF STATEMENT ===
        if (savedInstanceState != null) {
            lemonadeState = savedInstanceState.getString(LEMONADE_STATE, "select")
            lemonSize = savedInstanceState.getInt(LEMON_SIZE, -1)
            squeezeCount = savedInstanceState.getInt(SQUEEZE_COUNT, -1)
        }
        // === END IF STATEMENT ===

        lemonImage = findViewById(R.id.image_lemon_state)
        setViewElements()

        lemonImage!!.setOnClickListener {
            clickLemonImage()
        }

        lemonImage!!.setOnLongClickListener {

            showSnackbar()
            false
        }

    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     *
     * This method saves the state of the app if it is put in the background.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(LEMONADE_STATE, lemonadeState)
        outState.putInt(LEMON_SIZE, lemonSize)
        outState.putInt(SQUEEZE_COUNT, squeezeCount)
        super.onSaveInstanceState(outState)
    }



    /**
     * Clicking will elicit a different response depending on the state.
     * This method determines the state and proceeds with the correct action.
     */
    private fun clickLemonImage() {
        // TODO: use a conditional statement like 'if' or 'when' to track the lemonadeState
        //  when the the image is clicked we may need to change state to the next step in the
        //  lemonade making progression (or at least make some changes to the current state in the
        //  case of squeezing the lemon). That should be done in this conditional statement


        // TODO: When the image is clicked in the SELECT state, the state should become SQUEEZE
        //  - The lemonSize variable needs to be set using the 'pick()' method in the LemonTree class
        //  - The squeezeCount should be 0 since we haven't squeezed any lemons just yet.
        if (lemonadeState == SELECT) { //verifica o valor da variavel

            lemonadeState = SQUEEZE // atribui um novo valor a variavel
            lemonSize = lemonTree.pick() //chamando a classe = classe.método
            squeezeCount = 0

        } else if (lemonadeState == SQUEEZE) {
            squeezeCount += 1
            lemonSize -= 1
            if (lemonSize == 0) {
                lemonadeState = DRINK
                Toast.makeText(this, "WoW! Vc espremeu $squeezeCount vezes!!!", Toast.LENGTH_SHORT).show()
                lemonSize = -1
            }

        } else if (lemonadeState == DRINK) {
            lemonadeState = RESTART

        } else {
            lemonadeState = SELECT
        }

        // TODO: lastly, before the function terminates we need to set the view elements so that the
        //  UI can reflect the correct state
        setViewElements()
    }

    /**
     * Set up the view elements according to the state.
     */
    private fun setViewElements() {

        val textAction: TextView = findViewById(R.id.text_action)
        val imgAction: ImageView = findViewById(R.id.image_lemon_state)

        val imgResource = when (lemonadeState) {
            SELECT -> R.drawable.lemon_tree
            SQUEEZE-> R.drawable.lemon_squeeze
            DRINK-> R.drawable.lemon_drink
            else -> R.drawable.lemon_restart /* else : correção do erro when */
        }

        val textResource = when (lemonadeState) {
            SELECT-> R.string.lemon_select
            SQUEEZE-> R.string.lemon_squeeze
            DRINK -> R.string.lemon_drink
            else -> R.string.lemon_empty_glass//correção do erro when

        }

        // Update the ImageView with the correct drawable resource ID
        textAction.setText(textResource)
        imgAction.setImageResource(imgResource)
        // TODO: set up a conditional that tracks the lemonadeState

        // TODO: for each state, the textAction TextView should be set to the corresponding string from
        //  the string resources file. The strings are named to match the state

        // TODO: Additionally, for each state, the lemonImage should be set to the corresponding
        //  drawable from the drawable resources. The drawables have the same names as the strings
        //  but remember that they are drawables, not strings.
    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     *
     * Long clicking the lemon image will show how many times the lemon has been squeezed.
     */
    private fun showSnackbar(): Boolean {
        if (lemonadeState != SQUEEZE) {
            return false
        }

        val squeezeText = getString(R.string.squeeze_count, squeezeCount)
        Snackbar.make(
                findViewById(R.id.constraint_Layout),
                squeezeText,
                Snackbar.LENGTH_SHORT
        ).show()
        return true
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
                val intent1 = Intent(this, MainActivity::class.java)
                startActivity(intent1)
                true
            }
            R.id.item_menu2 -> {
                val intent2 = Intent(this, DiceActivity::class.java)
                startActivity(intent2)
                true
            }
            R.id.item_menu3 -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    }

/**
 * A Lemon tree class with a method to "pick" a lemon. The "size" of the lemon is randomized
 * and determines how many times a lemon needs to be squeezed before you get lemonade.
 */
class LemonTree {
    fun pick(): Int {
        return (2..10).random()
    }
}