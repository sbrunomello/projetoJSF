package br.com.brunomello.pessoa.controller;


import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.com.brunomello.repository.PessoaRepository;

@Named(value="graficoPizzaPessoaController")
@RequestScoped
public class GraficoPizzaPessoaController {

	@Inject
	private PessoaRepository pessoaRepository;


	private PieChartModel pieChartModel;

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	@PostConstruct
	public  void init(){

		this.pieChartModel = new PieChartModel();

		this.MontaGrafico();
	}

	/***
	 * MONTA O GR�FICO NA P�GINA
	 */
	private void MontaGrafico(){

		//CONSULTA OS DADOS PARA MONTAR O GR�FICO
		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.GetOrigemPessoa();


		//INFORMANDO OS VALORES PARA MONTAR O GR�FICO
		hashtableRegistros.forEach((chave,valor) -> {


			pieChartModel.set(chave, valor);

		});

		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");


	}
}