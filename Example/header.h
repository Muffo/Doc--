#ifndef CONTATORE_H
#define CONTATORE_H

/*
 *   @author Andrea Grandi  andrea.grandi8@studio.unibo.it
 *   @version 1.0
 *   Classe contatore standard
 *   Per maggiori dettagli visita il sito http://www.contatore.it
 *   Per fare avanzare il contatore utilizzare #Contatore>incrementaContatore
 */	
class Contatore
{
public:
    // Costruttore di default
    Contatore();

    /*
    *	Costruttore con valore iniziale
    *	@valoreIniziale valore iniziale del contatore
    */
    Contatore(int valoreIniziale);
    
    /*
     *  Distruttore di default
     */
    ~Contatore();

    /* 
    *	Valore del contatore
    *	@return intero contenente il valore del contatore
    */
    int getValoreContatore();

    /*
    *	Incrementa il contatore di uno
    */
    void incrementaContatore();

    
    /*
     *  Operatore di somma tra due classi #Contatore
     */
    int operator+(Contatore c);


private:
    /*
	* Valore del contatore
	* Viene incrementato di una unita' con #Contatore>incrementaContatore
	*/
    int valoreContatore;
	int *puntatore;
	int array[10][10][ALPHA];
    
    /* 
     * Questo è veramente peso!
        Così funziona?
     */
    std::list<String> ciuppa;
};

#endif

#ifndef CONTATORE_DOPPIO_H
#define CONTATORE_DOPPIO_H


#include "contatore.h"

/*
* 	@author Andrea Grandi andrea.grandi8@studio.unibo.it
*	@version 1.0
* 	Classe contatore doppio, derivata da #Contatore>valoreContatore ottimo
* 	Attenzione a non scrivere #Contatore>valoreContator
*/
class ContatoreDoppio : Contatore
{
    public:
		/*
		*	Costruttore con valore iniziale
		*	@valoreIniziale valore iniziale del contatore
		*/
        ContatoreDoppio(int valoreIniziale);

		/*
		*	Incrementa il valore del contatore di DUE unita
		*/
        void incrementaContatore();
}


/*
* 	@author Qualcuno qualcuno@studio.unibo.it
*	@version 0.5b
* 	Classe contatore indietro, derivata da #Contatore
*/
class ContatoreIndietro : Contatore
{
    public:
		/*
		*	Costruttore con valore iniziale
		*	@valoreIniziale valore iniziale del contatore
		*/
        ContatoreIndietro(const int valoreIniziale);

		/*
		*	Decrementa il valore del contatore di una unita
		*/
        void decrementaContatore();
};

#endif



