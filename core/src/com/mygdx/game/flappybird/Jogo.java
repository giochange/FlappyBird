package com.mygdx.game.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.ScreenUtils;

public class Jogo extends ApplicationAdapter {

	private int movimentaY = 0; //Movimentação do eixo Y
	private int movimentaX = 1000; //Movimentação do eixo X
	private SpriteBatch batch; //Quantidades de sprites que vai ser criado
	private Texture[] passaros; //As imagens dos sprites
	private Texture cano;
	private Texture cano2;
	private Texture fundo; //A imagem de fundo

	private float larguraDispositivo; //Largura referente ao Plano de Fundo
	private float alturaDispositivo; //Altura referente ao Plano de Fundo
	private float variacao = 0; //Variação das imagens pré setadas  do passaro- cria a sensação de estar voando
	private float gravidade = 0; //Gravidade do pasaro conforme se tem a  movimenta
	private float posicaoInicialVerticalPassaro = 0; //Posição inicial do objetos que  vai se iniciar no zero antes setado



	@Override
	public void create () {
		batch = new SpriteBatch(); //Instanciando um novo conjunto  de sprites
		passaros = new Texture[3]; //array das imagens do passaro
		passaros[0] = new Texture("passaro1.png"); //Imagem do passaro 1
		passaros[1] = new Texture("passaro2.png"); //Imagem do passaro 2
		passaros[2] = new Texture("passaro3.png"); //Imagem do passaro 3
		cano = new Texture("cano_topo_maior.png"); //Imagem do cano superior
		cano2 = new Texture("cano_baixo_maior.png"); //Imagem do cano inferior
		fundo = new Texture("fundo.png"); //Imagem do pano de fundo

		larguraDispositivo = Gdx.graphics.getWidth(); //Pegada da largura do plano de fundo
		alturaDispositivo = Gdx.graphics.getHeight(); //Pegada da altura do plano de fundo
		posicaoInicialVerticalPassaro = alturaDispositivo / 2; //Fazer com que  a posição inicial que antes se encontrava no '0' agora  fica entre o centro da tela


	}

	@Override
	public void render () {

		batch.begin(); //Inicio dos processos

		if(variacao > 3) //Se a imagens do sprites for maior que 3, variação =0;
			variacao = 0; //A imagem sendo  maior que 3 se retorna ao valor de '0'
		boolean toqueTela = Gdx.input.justTouched(); //bolleano de ação   perante toque tela
		if(Gdx.input.justTouched()){ //condicionamente segundo realização de toque na tela
			gravidade = -25; //Faz o passaro voar  para cima ao ter algum  toque na tela
		}

		if(posicaoInicialVerticalPassaro > 0 || toqueTela) // a posição inicial sendo  maior que zero e tocar na tela realiza resultado
			posicaoInicialVerticalPassaro = posicaoInicialVerticalPassaro - gravidade; //Faz o passaro voar somente ao toque na tela

		batch.draw(fundo, 0, 0, larguraDispositivo, alturaDispositivo); //realiza a construção da imagem de fundo dentro do jogo
		batch.draw(passaros[(int) variacao], 30, posicaoInicialVerticalPassaro); //faz o desenho do posicionamento do passaro
		 batch.draw(cano, movimentaX, 1000); //movimentação do cano superior
		 batch.draw(cano2, movimentaX,- 400); //movimentação do cano inferior

		variacao += Gdx.graphics.getDeltaTime() * 10; // faz uma balanceada na animação de bater as asas do passaro ao multiplicar por 10 fazendo ficar fluido

		gravidade++; //contador para gravidade
		movimentaX--; //Adiciona Movimentação do eixo x
		movimentaY--; //Adiciona Movimentação do eixo y
		batch.end(); //Fim do processo
	}

	@Override
	public void dispose () {

	}

}
