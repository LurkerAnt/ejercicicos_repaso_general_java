
package biblioteca;

import java.util.Scanner;

public class Libro {

	private String tituloDelLibro;
	private String autorDelLibro;
	private String isbnDelLibro;

	private Scanner teclado = new Scanner(System.in);

	public Libro(String tituloLibro, String autorLibro, String isbnLibro) {
		this.tituloDelLibro = tituloLibro;
		this.autorDelLibro = autorLibro;
		if (validarIsbn10(isbnLibro) == true || validarIsbn13(isbnLibro)==true) {
			this.isbnDelLibro = isbnLibro;
		} else {
			System.out.println("ISBN no valido.");
			setIsbnDelLibroTrasErrorPorTeclado();
		}

	}

	// getters, dan información

	public String getTituloDelLibro() {
		return tituloDelLibro;
	}

	public String getAutorDelLibro() {
		return autorDelLibro;
	}

	public String getIsbnDelLibro() {
		return isbnDelLibro;
	}

	// setters, escriben información.

	public void setTituloDelLibro(String tituloDelLibro) {
		this.tituloDelLibro = tituloDelLibro;
	}

	public void setAutorDelLibro(String autorDelLibro) {
		this.autorDelLibro = autorDelLibro;
	}
	
	//metodo de set libro que comprueba el isbn, si no es valido lo manda al siguientemetodo
	
	public void setIsbnDelLibro(String isbnDelLibro) {
		if (validarIsbn10(isbnDelLibro) == true|| validarIsbn13(isbnDelLibro)==true)  {
			this.isbnDelLibro = isbnDelLibro;
		} else {
			System.out.println("ISBN no valido.");
			setIsbnDelLibroTrasErrorPorTeclado();
		}
	}
	
	//metodo para introducir un isbn por teclado tras un error.
	
	public void setIsbnDelLibroTrasErrorPorTeclado() {

		System.out.println("Introduzca un nuevo ISBN valido: ");
		String nuevoISBN = teclado.nextLine();
		nuevoISBN.replaceAll(" ", "");
		if (validarIsbn10(nuevoISBN) == true|| validarIsbn13(nuevoISBN)==true)  {
			this.isbnDelLibro = nuevoISBN;
		} else {
			System.out.println("ISBN no valido.");
			setIsbnDelLibroTrasErrorPorTeclado();
		}

	}

	

	private boolean validarIsbn10(String isbn) {
		if (isbn == null) {
			return false;
		}

		// quita los -
		isbn = isbn.replaceAll("-", "");

		// comprueba la longitud
		if (isbn.length() != 10) {
			return false;
		}

		// utiliza la formula de comprobación de isbn

		try {
			int total = 0;
			for (int i = 0; i < 9; i++) {
				int digit = Integer.parseInt(isbn.substring(i, i + 1));
				total += ((10 - i) * digit);
			}

			String checksum = Integer.toString((11 - (total % 11)) % 11);
			if ("10".equals(checksum)) {
				checksum = "X";
			}

			return checksum.equals(isbn.substring(9));
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public boolean validarIsbn13(String isbn) {
		if (isbn == null) {
			return false;
		}

		// remove any hyphens
		isbn = isbn.replaceAll("-", "");

		// must be a 13 digit ISBN
		if (isbn.length() != 13) {
			return false;
		}

		try {
			int tot = 0;
			for (int i = 0; i < 12; i++) {
				int digit = Integer.parseInt(isbn.substring(i, i + 1));
				tot += (i % 2 == 0) ? digit * 1 : digit * 3;
			}

			// checksum must be 0-9. If calculated as 10 then = 0
			int checksum = 10 - (tot % 10);
			if (checksum == 10) {
				checksum = 0;
			}

			return checksum == Integer.parseInt(isbn.substring(12));
		} catch (NumberFormatException nfe) {
			// to catch invalid ISBNs that have non-numeric characters in them
			return false;
		}
	}

	@Override
	public String toString() {
		return "Libro [tituloDelLibro=" + tituloDelLibro + ", autorDelLibro=" + autorDelLibro + ", isbnDelLibro="
				+ isbnDelLibro + ", teclado=" + teclado + "]";
	}

	
}
