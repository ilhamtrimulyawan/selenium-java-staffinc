Feature: 'search'

  @search-staffinc
  Scenario: Search basedon keyword Bandung result validation
    Given user visits the Staffinc website
    When user click the search button
    And user search with choose Preferensi Kerja "Lokasi Pekerjaan"
    And user choose Provinsi "Jawa Barat"
    And user choose Kota "Bandung"
    And user click Simpan Preferensi Kerja
    Then user verify that get notification Success Filter
    And user click Lihat Semua Button
    Then user verify that search result contains "Bandung" appears
