package bnccMidProject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	ArrayList<Karyawan> listKaryawan = new ArrayList<Karyawan>();

	public Main() {

		int choose = 0;
	
		while(true) {
			printMenu();
			try {
				choose = scan.nextInt();
			} catch (Exception e) {
				System.err.println("Inputkan angka");
			}
			scan.nextLine();
			
			if(choose == 1) {
				
				insertData();
				choose = 0;
				
			} else if(choose == 2) {
				
				viewData();
				choose = 0;
				
			} else if(choose == 3) {
				
				updateData();
				choose = 0;
				
			} else if(choose == 4) {
				
				deleteData();
				choose = 0;
				
			} else if(choose == 5) {
				
				System.exit(0);
				
			}
		}
		

	}

	public void deleteData() {

		if (listKaryawan.isEmpty()) {
			viewData();
		} else {

			viewData();
			int choose = 0;
			while (true) {
				System.out.print("Input nomor urutan yang ingin dihapus: ");
				try {
					choose = scan.nextInt();
				} catch (Exception e) {
					System.err.println("Inputkan angka");
				}
				scan.nextLine();
				if ((choose - 1) > listKaryawan.size() && (choose - 1) < 0) {
					System.err.println("NOMOR TIDAK DITEMUKAN!");
				} else
					break;
			}
			listKaryawan.remove(choose - 1);
		}

	}

	public void updateData() {

		if (listKaryawan.isEmpty()) {
			viewData();
		} else {
			viewData();
			int choose = 0;
			while (true) {
				System.out.print("Input nomor urutan yang ingin diupdate: ");
				try {
					choose = scan.nextInt();
				} catch (Exception e) {
					System.err.println("Inputkan angka");
				}
				scan.nextLine();
				if ((choose - 1) > listKaryawan.size() && (choose - 1) < 0) {
					System.err.println("NOMOR TIDAK DITEMUKAN!");
				} else
					break;
			}

			String nama = "";
			String gender = "";
			String jabatan = "";
			float gajiKaryawan = 0;

			while (true) {
				System.out.print("Input nama karyawan [>=3]: ");
				nama = scan.nextLine();
				if (isAlphabetic(nama))
					break;
			}

			while (true) {
				System.out.print("Input jenis kelamin [Laki-laki/Perempuan]: ");
				gender = scan.nextLine();
				if (gender.equals("Laki-laki") || gender.equals("Perempuan"))
					break;
			}

			while (true) {
				System.out.print("Input Jabatan [Manager/Supervisor/Admin]: ");
				jabatan = scan.nextLine();
				if (jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin"))
					break;
			}
			switch (jabatan) {
			case "Manager":
				gajiKaryawan = 8000000;
				break;
			case "Supervisor":
				gajiKaryawan = 6000000;
				break;
			case "Admin":
				gajiKaryawan = 4000000;
				break;
			}
			bonusGaji(jabatan);

			listKaryawan.get(choose - 1).nama = nama;
			listKaryawan.get(choose - 1).gender = gender;
			listKaryawan.get(choose - 1).jabatan = jabatan;
			listKaryawan.get(choose - 1).gajiKaryawan = gajiKaryawan;
		}

	}

	public void viewData() {
		
		if(listKaryawan.isEmpty()) {
			System.out.println("Tidak ada karyawan!");
		} else {
		sorting();

		System.out.println(
				"===============================================================================================");
		System.out.printf("| %-3s | %-14s | %-20s | %-14s | %-12s | %-13s |\n", "No", "Kode Karyawan", "Nama Karyawan",
				"Jenis Kelamin", "Jabatan", "Gaji Karyawan");
		System.out.println(
				"===============================================================================================");
		for (int i = 0; i < listKaryawan.size(); i++) {
			System.out.printf("| %-3s | %14s | %20s | %14s | %12s | %13s |\n", (i + 1), listKaryawan.get(i).ID,
					listKaryawan.get(i).nama, listKaryawan.get(i).gender, listKaryawan.get(i).jabatan,
					listKaryawan.get(i).gajiKaryawan);
		}
		System.out.println(
				"===============================================================================================");
		}	
	}

	public void insertData() {
		String ID = ID();
		String nama = "";
		String gender = "";
		String jabatan = "";
		float gajiKaryawan = 0;

		while (true) {
			System.out.print("Input nama karyawan [>=3]: ");
			nama = scan.nextLine();
			if (isAlphabetic(nama))
				break;
		}

		while (true) {
			System.out.print("Input jenis kelamin [Laki-laki/Perempuan]: ");
			gender = scan.nextLine();
			if (gender.equals("Laki-laki") || gender.equals("Perempuan"))
				break;
		}

		while (true) {
			System.out.print("Input Jabatan [Manager/Supervisor/Admin]: ");
			jabatan = scan.nextLine();
			if (jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin"))
				break;
		}
		switch (jabatan) {
		case "Manager":
			gajiKaryawan = 8000000;
			break;
		case "Supervisor":
			gajiKaryawan = 6000000;
			break;
		case "Admin":
			gajiKaryawan = 4000000;
			break;
		}
		bonusGaji(jabatan);

		listKaryawan.add(new Karyawan(ID, nama, gender, jabatan, gajiKaryawan));
		System.out.println("Berhasil menambahkan karyawan dengan ID " + ID);

	}

	public void printMenu() {
		System.out.println();
		System.out.println("Chipichapa");
		System.out.println("=======================");
		System.out.println("1. Insert data karyawan");
		System.out.println("2. View data karyawan");
		System.out.println("3. Update data karyawan");
		System.out.println("4. Delete data karyawan");
		System.out.println("5. Exit");
		System.out.print(">> ");
	}

	public String ID() {
		String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String ID = "";

		for (int i = 0; i < 2; i++) {
			ID += Alphabet.charAt(rand.nextInt(Alphabet.length() - 1));
		}

		ID += "-";

		for (int i = 0; i < 4; i++) {
			ID += rand.nextInt(10);
		}

		return ID;
	}

	public boolean isAlphabetic(String input) {
		boolean isNumber = false;
		for (int i = 0; i < input.length(); i++) {
			if (Character.isDigit(input.charAt(i))) {
				isNumber = true;
			}
		}
		if (isNumber == true) {
			return false;
		}

		return true;
	}

	public void sorting() {
		int size = listKaryawan.size();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				Karyawan data1 = listKaryawan.get(j);
				Karyawan data2 = listKaryawan.get(j + 1);
				String compare1 = listKaryawan.get(j).nama;
				String compare2 = listKaryawan.get(j + 1).nama;
				if (compare1.compareTo(compare2) > 0) {
					listKaryawan.set(j, data2);
					listKaryawan.set(j + 1, data1);
				}
			}
		}

	}

	public void bonusGaji(String newInput) {

		int counter = 0;
		for (int i = 0; i < listKaryawan.size(); i++) {
			if (newInput.equals(listKaryawan.get(i).jabatan)) {
				counter++;
			}
		}
		
		if (counter % 3 == 0) {
			for (int i = 0; i < listKaryawan.size(); i++) {
				if (newInput.equals(listKaryawan.get(i).jabatan)) {
					if (newInput.equals("Manager")) {
						listKaryawan.get(i).gajiKaryawan += (float) (10.0 / 100.0) * listKaryawan.get(i).gajiKaryawan;
					} else if (newInput.equals("Supervisor")) {
						listKaryawan.get(i).gajiKaryawan += (float) (7.5 / 100.0) * listKaryawan.get(i).gajiKaryawan;
					} else if (newInput.equals("Admin")) {
						listKaryawan.get(i).gajiKaryawan += (float) (5.0 / 100.0) * listKaryawan.get(i).gajiKaryawan;
					}

				}
			}

		}

	}

	public static void main(String[] args) {
		new Main();

	}

}
