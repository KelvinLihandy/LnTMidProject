package Main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import Data.Karyawan;
import java.util.Collections;


public class Main {
	ArrayList<Karyawan> dataKaryawan = new ArrayList<Karyawan>();
	Scanner scan = new Scanner(System.in);
	private final String HURUF = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final int PANJANG = 26;
	Random angka = new Random();
	private final int GAJI_MANAGER = 8000000;
	private final int GAJI_SUPERVISOR = 6000000;
	private final int GAJI_ADMIN = 4000000;
	int jumlahManager = 0;
	int jumlahSupervisor = 0;
	int jumlahAdmin = 0;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		while(true) {
			int choice = Menu(scan);
			scan.nextLine();
			switch(choice) {
				case 1:
					InputData();
					Return();
					break;
				case 2:
					ViewData();
					Return();
					break;
				case 3:
					UpdateData();
					Return();
					break;
				case 4:
					DeleteData();
					Return();
					
					break;
				case 5:
					System.out.println("Terima kasih telah menggunakan program ini");
					System.exit(0);
					break;
				default:
					System.out.println("Pilihan tidak ada");
					break;
			}
			
		}
	}
	
	public void Return() {
		String console = null;
		do{
			System.out.println("ENTER to return");
			console = scan.nextLine();
		}while(!console.equals(""));
	}
	
	public String GenerateID() {
		StringBuilder id = new StringBuilder();
		for(int i = 0; i < 7; i++) {
			if(i < 2) id.append(HURUF.charAt(angka.nextInt(PANJANG)));
			else if(i == 2) id.append("-");
			else if(i > 2) id.append(angka.nextInt(10));
		}
		return id.toString();
	}
	
	public void InputData() {
		String nama;
		String jenisKelamin;
		String jabatan;
		double gaji = 0;
		String idKaryawan = GenerateID();
		
		System.out.print("Input nama karyawan [>= 3]: ");
		nama = scan.nextLine();
		
		System.out.print("Input jenis kelamin [Laki-Laki | Perempuan] (Case Sensitive): ");
		jenisKelamin = scan.nextLine();
		if(!(jenisKelamin.equals("Laki-Laki") || jenisKelamin.equals("Perempuan"))) return;
		
		System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
		jabatan = scan.nextLine();
		if(jabatan.equals("Manager")) {
			jumlahManager += 1;
			gaji = GAJI_MANAGER;
		}
		else if(jabatan.equals("Supervisor")) {
			jumlahSupervisor += 1;
			gaji = GAJI_SUPERVISOR;
		}
		else if(jabatan.equals("Admin")) {
			jumlahAdmin += 1;
			gaji = GAJI_ADMIN;
		}
		else return;
		
		System.out.println("Berhasil menambahkan karyawan dengan id " + idKaryawan);
		Karyawan karyawan = new Karyawan(nama, jenisKelamin, jabatan, idKaryawan, gaji);
		dataKaryawan.add(karyawan);
		
		if((jumlahManager - 1) % 3 == 0 && jumlahManager - 1 != 0) {
			int counter = 1;
			System.out.print("Bonus sebesar 10% telah ditambahkan kepada karyawan dengan id");
			for(Karyawan k: dataKaryawan) {
				if(counter > (jumlahManager - 1)) break;
				if(k.getJabatan().equals("Manager")) {
					k.setGaji(k.getGaji() * 1.10);
					if(counter == 1) System.out.print(" " + k.getIdKaryawan());
					else if(counter > 1) System.out.print(", " + k.getIdKaryawan());
					counter += 1;
				}
			}
			System.out.println("");
		}
		else if((jumlahSupervisor - 1) % 3 == 0 && jumlahSupervisor - 1 != 0) {
			int counter = 1;
			System.out.print("Bonus sebesar 7.5% telah ditambahkan kepada karyawan dengan id");
			for(Karyawan k: dataKaryawan) {
				if(counter > (jumlahSupervisor - 1)) break;
				if(k.getJabatan().equals("Supervisor")) {
					k.setGaji(k.getGaji() * 1.075);
					if(counter == 1) System.out.print(" " + k.getIdKaryawan());
					else if(counter > 1) System.out.print(", " + k.getIdKaryawan());
					counter += 1;
				}
			}
			System.out.println("\n");
		}
		else if((jumlahAdmin - 1) % 3 == 0 && jumlahAdmin -1 != 0) {
			int counter = 1;
			System.out.print("Bonus sebesar 5% telah ditambahkan kepada karyawan dengan id");
			for(Karyawan k: dataKaryawan) {
				if(counter > (jumlahAdmin - 1)) break;
				if(k.getJabatan().equals("Admin")) {
					k.setGaji(k.getGaji() * 1.05);
					if(counter == 1) System.out.print(" " + k.getIdKaryawan());
					else if(counter > 1) System.out.print(", " + k.getIdKaryawan());
					counter += 1;
				}
			}
			System.out.println("");
		}
	}
	
	public void BubbleSort(ArrayList<Karyawan> dataKaryawan) {
		int n = dataKaryawan.size();
		for(int i = 1; i < n; i++) {
			for(int j = i; j < n; j++) {
				Karyawan karyawan1 = dataKaryawan.get(j-1);
				Karyawan karyawan2= dataKaryawan.get(j);
				if(karyawan1.getNama().compareTo(karyawan2.getNama()) > 0) {
					Collections.swap(dataKaryawan, j-1, j);
				}
			}
		}
	}
	
	public void ViewData() {
		BubbleSort(dataKaryawan);
		System.out.println("|----|-----------------|------------------------------|---------------|---------------|---------------|");
		System.out.println("|No  |Kode Karyawan    |Nama Karyawan                 |Jenis Kelamin  |Jabatan        |Gaji Karyawan  |");
		System.out.println("|----|-----------------|------------------------------|---------------|---------------|---------------|");
		int count = dataKaryawan.size();
		for(int i = 1; i <= count; i++) {
			Karyawan k = dataKaryawan.get(i-1);
			System.out.printf("|%4d|%17s|%30s|%15s|%15s|%15d|%n",
			i, k.getIdKaryawan(), k.getNama(), k.getJenisKelamin(), k.getJabatan(), (int)k.getGaji());
		}
		System.out.println("|----|-----------------|------------------------------|---------------|---------------|---------------|");
	}
	
	public void UpdateData() {
		ViewData();
		int nomor;
		String nama;
		String jenisKelamin;
		String jabatan;
		String idKaryawan = GenerateID();
		double gaji = 0;
		
		System.out.println("Input nomor urutan karyawan yang ingin diupdate: ");
		nomor = scan.nextInt();
		Karyawan k = dataKaryawan.get(nomor-1);
		scan.nextLine();
		
		System.out.print("Input nama karyawan [>= 3]: ");
		nama = scan.nextLine();
		if(nama.equals("0")) nama = k.getNama();
		
		System.out.print("Input jenis kelamin [Laki-Laki | Perempuan] (Case Sensitive): ");
		jenisKelamin = scan.nextLine();
		if(jenisKelamin.equals("0")) jenisKelamin = k.getJenisKelamin();
		else if(!jenisKelamin.equals("Laki-Laki") || !jenisKelamin.equals("Perempuan")) return;
		
		System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
		jabatan = scan.nextLine();
		if(jabatan.equals("0")) {
			jabatan = k.getJabatan();
			if(k.getJabatan().equals("Manager")) {
				gaji = GAJI_MANAGER;
			}
			else if(k.getJabatan().equals("Supervisor")) {
				gaji = GAJI_SUPERVISOR;
			}
			else if(k.getJabatan().equals("Admin")) {
				gaji = GAJI_ADMIN;
			}
		}
		else if(jabatan.equals("Supervisor") && k.getJabatan().equals("Manager")) {
			jumlahSupervisor += 1;
			jumlahManager -= 1;
			gaji = GAJI_SUPERVISOR;
		}
		else if(jabatan.equals("Admin") && k.getJabatan().equals("Manager")) {
			jumlahAdmin += 1;
			jumlahManager -= 1;
			gaji = GAJI_ADMIN;
		}
		else if(jabatan.equals("Manager") && k.getJabatan().equals("Supervisor")) {
			jumlahManager += 1;
			jumlahSupervisor -= 1;
			gaji = GAJI_MANAGER;
		}
		else if(jabatan.equals("Admin") && k.getJabatan().equals("Supervisor")) {
			jumlahAdmin += 1;
			jumlahSupervisor -= 1;
			gaji = GAJI_ADMIN;
		}
		else if(jabatan.equals("Manager") && k.getJabatan().equals("Admin")) {
			jumlahManager += 1;
			jumlahAdmin -= 1;
			gaji = GAJI_MANAGER;
		}
		else if(jabatan.equals("Supervisor") && k.getJabatan().equals("Admin")) {
			jumlahSupervisor += 1;
			jumlahAdmin -= 1;
			gaji = GAJI_SUPERVISOR;
		}
		else return;
		
		System.out.println("Berhasil mengupdate karyawan dengan id " + idKaryawan);
			
		Karyawan kNew = new Karyawan(nama, jenisKelamin, jabatan, idKaryawan, gaji);
		dataKaryawan.set(nomor-1, kNew);
	}
	
	public void DeleteData() {
		ViewData();
		int nomor;
		
		System.out.println("Input nomor urutan karyawan yang ingin dihapus: ");
		nomor = scan.nextInt();
		scan.nextLine();
		dataKaryawan.remove(nomor-1);
	}
	
	public int Menu(Scanner scan) {
		System.out.println("Selamat datang di PT ChipiChapa");
		System.out.println("Harap pilih salah satu operasi di bawah");
		System.out.println("1. Insert data karyawan");
		System.out.println("2. View data karyawan");
		System.out.println("3. Update data karyawan");
		System.out.println("4. Delete data karyawan");
		System.out.println("5. Exit program");
		return scan.nextInt();
	}
}
